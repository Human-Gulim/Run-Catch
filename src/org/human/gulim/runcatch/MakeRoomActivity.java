package org.human.gulim.runcatch;

import org.human.gulim.runcatch.bean.RoomInfo;
import org.human.gulim.runcatch.exception.NetworkMethodException;
import org.human.gulim.runcatch.factory.NetworkMethodFactory;
import org.human.gulim.runcatch.network.NetworkMethod;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MakeRoomActivity extends Activity {

	public static RoomInfo roomInfo = new RoomInfo();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_room);
	}

	public void makeRoom (View v)
	{
		// 서버에 데이터를 보내 방을 만들게 함
		

		BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();

		String mac = BTAdapter.getAddress();

		EditText time = (EditText) findViewById (R.id.gameTimeText);
		EditText distance = (EditText) findViewById (R.id.gameDistanceText);
		
		roomInfo.setId_room(mac);
		roomInfo.setTime(Long.parseLong(time.getText().toString()));
		
		class CreateRoomTask extends AsyncTask<Void, Void, RoomInfo>
		{
			
			@Override
			protected void onPostExecute(RoomInfo result) {
				roomInfo = result;
				super.onPostExecute(result);
			}

			@Override
			protected RoomInfo doInBackground(Void... params) {
				
				NetworkMethod toServ = NetworkMethodFactory.getInstance();
				RoomInfo roomInfo = null;
				
				try {
					roomInfo = toServ.emitEvent(NetworkMethod.CREATE_ROOM, roomInfo);
				} catch (NetworkMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
			}
			
		}
		
		CreateRoomTask task = new CreateRoomTask ();
		task.execute();
		
		// 다음 화면으로

		Intent intent = new Intent (this, WaitUserActivity.class);
		startActivity(intent);

		finish();
	}
}
