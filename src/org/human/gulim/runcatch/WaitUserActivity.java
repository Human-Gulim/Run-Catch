package org.human.gulim.runcatch;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WaitUserActivity extends Activity {
	ArrayList<String> list;
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

		list = new ArrayList<String>();

		adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, list);

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
					String [] lists = message.split("\n");
					memberAdd(lists[0] + "(" + lists[1] + ")");
					
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


	public void memberAdd (String data)
	{
		list.add(data);
		adapter.notifyDataSetChanged();
	}

}
