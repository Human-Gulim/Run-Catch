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

		// �대��꾪솕��NFC媛�爰쇱졇 �덈떎硫� �쒖꽦���좊룄

		if (!NfcAdapter.getDefaultAdapter(getApplicationContext()).isEnabled())
		{
			Toast.makeText(getApplicationContext(), "NFC��Beam ���쒖꽦���쒗궓 �� Back �ㅻ� �뚮윭���깆쑝濡��뚯븘��＜�몄슂", Toast.LENGTH_LONG).show();
			startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		}

		// Android Beam 遺�텇
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);

		// Shared Preference�먯꽌 Nickname��媛�졇�ㅺ린
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String nickName = pref.getString("nickName", "");
		
		if ( nickName.equals(""))
		{
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("Exception", "�щ컮瑜�nickname �곗씠�곌� 議댁옱�섏� �딄굅�� nickname �곗씠�곌� 怨듬갚�낅땲��");
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		// Bluetooth ��MAC address瑜�媛�졇�ㅺ린
		
		BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
		final int REQUEST_ENABLE_BT = 1;
		
		if (BTAdapter.isEnabled())
		{
			// Discoverable 紐⑤뱶濡�
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
		
		// NFC濡�蹂대궪 �댁슜���앹꽦 - content
		String content = nickName + "\n" + mac;

		NdefRecord [] records = new NdefRecord[2];
		
		records[0] = NdefRecord.createUri(content);
		records[1] = NdefRecord.createApplicationRecord("org.human,gulim.runcatch");
		
		msg = new NdefMessage(records);
		
		Log.d("verbose", "Warning, ndef message is null");
		nfcAdapter.setNdefPushMessage(msg, thisActivity);

	}


}
