package org.human.gulim.runcatch;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String nickName = pref.getString("nickName", "");
		
		if ( nickName.equals(""))
		{
			// 닉네임이 존재하지 않기 때문에, 닉네임 설정 Activity를 실행
			
			Intent intent = new Intent (this, SetNickNameActivity.class);
			startActivity(intent);
		}
		
		// 센서 활성화 여부를 체크
		
		BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		boolean locEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if ( !BTAdapter.isEnabled() || !NfcAdapter.getDefaultAdapter(getApplicationContext()).isEnabled() || !locEnabled)
		{
			Intent intent = new Intent(this, CommunicationsEnableActivity.class);
			startActivity(intent);
		}
	}
	
	public void makeRoom (View v)
	{
		// 방을 만드는 Activity 실행
		Intent intent = new Intent (this, MakeRoomActivity.class);
		startActivity(intent);
		
		// 적절한 동작을 하고 현재 Activity 종료
		finish();
	}
	
	public void joinRoom (View v)
	{
		// 방에 참가하는 Activity 실행
		Intent intent = new Intent (this, JoinRoomActivity.class);
		startActivity(intent);
		
		// 적절한 동작을 하고 현재 Activity 종료
		finish();
	}
}
