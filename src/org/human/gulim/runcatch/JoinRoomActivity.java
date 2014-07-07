package org.human.gulim.runcatch;

import org.human.gulim.runcatch.bean.RoomInfo;
import org.human.gulim.runcatch.bean.User;
import org.human.gulim.runcatch.exception.NetworkMethodException;
import org.human.gulim.runcatch.factory.NetworkMethodFactory;
import org.human.gulim.runcatch.network.NetworkMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class JoinRoomActivity extends Activity {

	NfcAdapter nfcAdapter;
	NdefMessage msg;
	JoinRoomActivity thisActivity = this;

	public static User me = new User();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_room);


		// Android Beam 부분
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);

		// Shared Preference에서 Nickname을 가져오기
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		final String nickName = pref.getString("nickName", "");
		
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
	
		final String mac = BTAdapter.getAddress();
		
		
		me.setNickname(nickName);
		me.setId(mac);
		
		// NFC로 보낼 내용을 생성 - content
		//String content = nickName + "\n" + mac;

		String content = me.toJSONObject().toJSONString();
		//Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
		/*
		Log.d("verbose", content);
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(content);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("verbose", obj.toJSONString());
		*/
		
		NdefRecord [] records = new NdefRecord[2];
		
		records[0] = NdefRecord.createMime("text/plain", content.getBytes());
		//records[0] = NdefRecord.createUri(content);
		records[1] = NdefRecord.createApplicationRecord("org.human,gulim.runcatch");
		
		msg = new NdefMessage(records);
		
		//Log.d("verbose", "Warning, ndef message is null");
		nfcAdapter.setNdefPushMessage(msg, thisActivity);
		
		class SuccessBeamCallback implements OnNdefPushCompleteCallback{

			@Override
			public void onNdefPushComplete(NfcEvent event) {
				// 게임 시작을 기다린다는 내용의 UI로 변경
					/* Do something */
				// 서버 폴링 시작
				
				
				
				class PollStartStatusTask extends AsyncTask <Void, Void, RoomInfo>
				{
					@Override
					protected void onPostExecute(RoomInfo result) {
						// 만약 돌아온 응답이 null 이라면, 5초 후 다시 시도
						if ( result == null )
						{
							Log.d("error", "isStart returned null, retrying after 5 seconds");
							final PollStartStatusTask task = new PollStartStatusTask ();
							Handler handler = new Handler();
							
							handler.postDelayed(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									task.execute();
								}
								
							}, 5000);
						}
						super.onPostExecute(result);
					}

					@Override
					protected RoomInfo doInBackground(Void... params) {
						NetworkMethod toServ = NetworkMethodFactory.getInstance();
						RoomInfo roomInfo = null;
						
						try {
							roomInfo = toServ.emitEvent(NetworkMethod.IS_STARTED, me);
						} catch (NetworkMethodException e) {
							Log.d("error", "isStart checking failed");
						}
						
						return roomInfo;
					}
					
				}
				
				PollStartStatusTask task = new PollStartStatusTask();
				task.execute();
					
			}

		}
		
		SuccessBeamCallback callback = new SuccessBeamCallback();

		nfcAdapter.setOnNdefPushCompleteCallback(callback, this);

	}

	

}
