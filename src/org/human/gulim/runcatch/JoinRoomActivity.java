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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class JoinRoomActivity extends Activity {

	NfcAdapter nfcAdapter;
	NdefMessage msg;
	JoinRoomActivity thisActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_room);

		// 휴대전화의 NFC가 꺼져 있다면, 활성화 유도

		if (!NfcAdapter.getDefaultAdapter(getApplicationContext()).isEnabled())
		{
			Toast.makeText(getApplicationContext(), "NFC와 Beam 을 활성화 시킨 후, Back 키를 눌러서 앱으로 돌아와주세요", Toast.LENGTH_LONG).show();
			startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		}

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
		
		// Bluetooth 의 MAC address를 가져오기
		
		BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
		final int REQUEST_ENABLE_BT = 1;
		
		if (BTAdapter.isEnabled())
		{
			// Discoverable 모드로
			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);

			startActivity(discoverableIntent); // make adapter discoverable 
		}
		else
		{
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

			Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);

			startActivity(discoverableIntent); // make adapter discoverable 
		}
	
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
