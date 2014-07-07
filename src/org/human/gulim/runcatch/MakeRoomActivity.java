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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MakeRoomActivity extends Activity {

	public static RoomInfo roomInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_room);
	}

	public void makeRoom (final View v)
	{
		roomInfo = new RoomInfo();
		// 서버에 데이터를 보내 방을 만들게 함
		v.setEnabled(false);

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

				if ( result == null )
				{
					Toast.makeText(getApplicationContext(), "서버와 연결을 실패했습니다.\n잠시 후 다시 시도해주세요", Toast.LENGTH_LONG).show();
					v.setEnabled(true);
					
					this.cancel(true);
					return;
				}
				super.onPostExecute(result);

				roomInfo = result;
				// 다음 화면으로

				Intent intent = new Intent (getApplicationContext(), WaitUserActivity.class);
				startActivity(intent);

				finish();
			}

			@Override
			protected RoomInfo doInBackground(Void... params) {

				NetworkMethod toServ = NetworkMethodFactory.getInstance();

				try {
					roomInfo = toServ.emitEvent(NetworkMethod.CREATE_ROOM, roomInfo);
				} catch (NetworkMethodException e) {
					roomInfo = null;
				}

				return roomInfo;
			}

		}

		CreateRoomTask task = new CreateRoomTask ();
		task.execute();
	}
}
