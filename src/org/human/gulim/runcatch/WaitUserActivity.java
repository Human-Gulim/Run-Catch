package org.human.gulim.runcatch;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import org.human.gulim.runcatch.bean.RoomInfo;
import org.human.gulim.runcatch.bean.Team;
import org.human.gulim.runcatch.bean.User;
import org.human.gulim.runcatch.exception.NetworkMethodException;
import org.human.gulim.runcatch.factory.NetworkMethodFactory;
import org.human.gulim.runcatch.network.NetworkMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WaitUserActivity extends Activity {
	ArrayList<User> list;
	ArrayList<String> viewList;
	ArrayAdapter<String> adapter;
	NfcAdapter mNfcAdapter;
	IntentFilter[] mNdefExchangeFilters;
	PendingIntent mNfcPendingIntent;
	ListView lView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_user);

		lView = (ListView) findViewById (R.id.joinList);

		list = new ArrayList<User>();
		viewList = new ArrayList<String>();

		adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, viewList);

		lView.setAdapter(adapter);

		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

		mNfcAdapter.setNdefPushMessage(null, this);

		Intent intent = new Intent (this, getClass())
		.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mNfcPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

		// Intent filters for exchanging over p2p.
		IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		try {
			ndefDetected.addDataType("text/plain");
			//ndefDetected.addDataType("application/com.example.nfcp2preceiver");
		} catch (MalformedMimeTypeException e) {
			e.printStackTrace();
		}
		mNdefExchangeFilters = new IntentFilter[] { ndefDetected };

	}

	@Override
	protected void onNewIntent(Intent intent) {
		Log.d("verbose", "Get Intent");
		String action = intent.getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)){
			Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			NdefMessage[] messages;
			if (rawMsgs != null) {
				messages = new NdefMessage[rawMsgs.length];
				for (int i = 0; i < rawMsgs.length; i++) {
					messages[i] = (NdefMessage) rawMsgs[i];     
					// To get a NdefRecord and its different properties from a NdefMesssage   
					NdefRecord record = messages[i].getRecords()[i];
					byte[] id = record.getId();
					short tnf = record.getTnf();
					byte[] type = record.getType();

					//String message = getTextData(record.getPayload());

					String message = "";
					try {
						message = new String(record.getPayload(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//Log.d("verbose", "decoded text: " + message);

					JSONParser parser = new JSONParser();
					JSONObject obj = null;
					try {
						obj = (JSONObject) parser.parse(message);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					User newUser = User.getUserFromJson(obj);
					if ( newUser == null )
					{
						Log.d("verbose", "newUser is null");
					}
					memberAdd(newUser);

				}
			}
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, null, null);
	}

	// Decoding a payload containing text
	private String getTextData(byte[] payload) {
		if(payload == null) 
			return null;
		try {
			String encoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
			int langageCodeLength = payload[0] & 0077;
			return new String(payload, langageCodeLength + 1, payload.length - langageCodeLength - 1, encoding);     
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void memberAdd (User data)
	{
		list.add(data);
		viewList.add(data.getNickname() + "(" + data.getId() + ")");
		adapter.notifyDataSetChanged();
	}

	public void startGame (View v)
	{
		// 있는 방에 사람들을 참여시킴
		Team team1 = new Team();
		Team team2 = new Team();

		Random r = new Random();

		for (int i=0; i<list.size(); i++)
		{
			if ( r.nextInt() % 2 == 0 )
			{
				team1.put(list.get(i).getId(), list.get(i));
			}
			else
			{
				team2.put(list.get(i).getId(), list.get(i));
			}
		}

		MakeRoomActivity.roomInfo.putTeam(0, team1);
		MakeRoomActivity.roomInfo.putTeam(1, team2);

		class AddMemberTask extends AsyncTask <Void, Void, RoomInfo>
		{

			@Override
			protected void onPostExecute(RoomInfo result) {
				MakeRoomActivity.roomInfo = result;
			}

			@Override
			protected RoomInfo doInBackground(Void... params) {

				NetworkMethod toServ = NetworkMethodFactory.getInstance();
				try {
					toServ.emitEvent(NetworkMethod.ADD_ALL_MEMBERS, MakeRoomActivity.roomInfo);
				} catch (NetworkMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return MakeRoomActivity.roomInfo;
			}

		}

		AddMemberTask task = new AddMemberTask ();
		task.execute();

		// 게임 시작 activity로 전환 후 현재 activity 종료

		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);

		finish();
	}
}
