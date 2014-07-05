package org.human.gulim.runcatch;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class JoinRoomActivity extends Activity {

	NfcAdapter nfcAdapter;
	NdefMessage msg;
	JoinRoomActivity thisActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_room);


		// Android Beam 부분
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);

		// Shared Preference에서 Nickname을 가져오기
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String nickName = pref.getString("nickName", "");
		
		if ( nickName.equals(""))
		{
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("Exception", "올바른 nickname 데이터가 존재하지 않거나, nickname 데이터가 공백입니다");
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
	
		String mac = BTAdapter.getAddress();
		
		// NFC로 보낼 내용을 생성 - content
		String content = nickName + "\n" + mac;

		NdefRecord [] records = new NdefRecord[2];
		
		records[0] = NdefRecord.createUri(content);
		records[1] = NdefRecord.createApplicationRecord("org.human,gulim.runcatch");
		
		msg = new NdefMessage(records);
		
		Log.d("verbose", "Warning, ndef message is null");
		nfcAdapter.setNdefPushMessage(msg, thisActivity);

	}


}
