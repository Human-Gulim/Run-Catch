package org.human.gulim.runcatch;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;
import android.provider.Settings;

public class CommunicationsEnableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communications_enable);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Log.d("verbose", "onResume called");
		// 이미 활성화 된 기능이 있다면, 버튼 비활성화

		int i = 0;
		// BT

		BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();

		if ( BTAdapter.isEnabled() && BTAdapter.getScanMode() == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE )
		{
			Button btn = (Button) findViewById(R.id.btEnableBtn);

			btn.setText("Bluetooth 활성화 (이미 활성화 됨)");
			btn.setEnabled(false);
			i++;
		}

		// NFC

		if (NfcAdapter.getDefaultAdapter(getApplicationContext()).isEnabled())
		{
			Button btn = (Button) findViewById(R.id.nfcEnableBtn);

			btn.setText("NFC 활성화 (이미 활성화 됨)");
			btn.setEnabled(false);
			i++;
		}

		// GPS

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (enabled)
		{
			Button btn = (Button) findViewById(R.id.gpsEnableBtn);

			btn.setText("GPS 활성화 (이미 활성화 됨)");
			btn.setEnabled(false);
			i++;
		}
		
		if ( i == 3 )
		{
			Button btn = (Button) findViewById (R.id.contBtn);
			
			btn.setEnabled(true);
		}
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
	}

	public void nfcEnable (View v)
	{

		// 휴대전화의 NFC가 꺼져 있다면, 활성화 유도

		if (!NfcAdapter.getDefaultAdapter(getApplicationContext()).isEnabled())
		{
			Toast.makeText(getApplicationContext(), "NFC와 Beam 을 활성화 시킨 후, Back 키를 눌러서 앱으로 돌아와주세요", Toast.LENGTH_LONG).show();
			startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		}
	}

	public void gpsEnable (View v)
	{
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (!enabled)
		{
			Toast.makeText(getApplicationContext(), 
					"GPS를 활성화 한 후 (정확한 위치) Back 키를 눌러서 앱으로 돌아와 주세요!", 
					Toast.LENGTH_LONG
					).show();
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);

			return;
		}
	}

	public void continueGame (View v)
	{
		finish();
	}

}
