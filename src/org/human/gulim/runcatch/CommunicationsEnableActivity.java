package org.human.gulim.runcatch;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class CommunicationsEnableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communications_enable);
	}

	public void btEnable (View v)
	{
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
		
		// 동작 후 validation
		
		if ( BTAdapter.isEnabled() && BTAdapter.isDiscovering() )
		{
			Button btn = (Button) findViewById(R.id.btEnableBtn);
			
			btn.setText("Bluetooth 활성화 (이미 활성화 됨)");
			btn.setEnabled(false);
		}
	}

	public void nfcEnable (View v)
	{

		// 휴대전화의 NFC가 꺼져 있다면, 활성화 유도

		if (!NfcAdapter.getDefaultAdapter(getApplicationContext()).isEnabled())
		{
			Toast.makeText(getApplicationContext(), "NFC와 Beam 을 활성화 시킨 후, Back 키를 눌러서 앱으로 돌아와주세요", Toast.LENGTH_LONG).show();
			startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		}
		
		// 동작 후 validation
		
		if (NfcAdapter.getDefaultAdapter(getApplicationContext()).isEnabled())
		{
			Button btn = (Button) findViewById(R.id.nfcEnableBtn);
			
			btn.setText("NFC 활성화 (이미 활성화 됨)");
			btn.setEnabled(false);
		}
	}

	public void gpsEnable (View v)
	{

	}

	public void continueGame (View v)
	{
		finish();
	}

}
