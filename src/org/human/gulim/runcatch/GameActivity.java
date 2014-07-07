package org.human.gulim.runcatch;

import org.human.gulim.runcatch.bean.RoomInfo;
import org.human.gulim.runcatch.bean.Team;
import org.human.gulim.runcatch.bean.User;
import org.human.gulim.runcatch.exception.NetworkMethodException;
import org.human.gulim.runcatch.factory.NetworkMethodFactory;
import org.human.gulim.runcatch.network.NetworkMethod;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity {

	public static GameActivity me;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		me = this;
		
		Intent intent = new Intent(getBaseContext(), LocationFetchService.class);
		startService(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// TODO Auto-generated method stub
		Intent intent = new Intent(getBaseContext(), LocationFetchService.class);
		stopService(intent);
	}

	public void itemUse (View v)
	{
		if ( v.getId() == R.id.item1Icn )
		{
			// 팝업 창 띄우기
			TextView lbl = (TextView) findViewById(R.id.item1Lbl);
			boolean itemExist = true;
			View layoutView = LayoutInflater.from(this).inflate(R.layout.dialog_item_use, null);

			final TextView itemName = (TextView) layoutView.findViewById(R.id.itemNameLbl);
			final TextView itemDesc = (TextView) layoutView.findViewById(R.id.itemDescLbl);
			final ImageView itemImg = (ImageView) layoutView.findViewById(R.id.itemImgView);
			final Button closeDialog = (Button) layoutView.findViewById(R.id.exitBtn);

			itemName.setText("투시 안경");
			itemDesc.setText("이거슨 투시 안경이다");

			if ( !itemExist )
			{
				// 라벨의 데이터에 따라 아이템의 종류를 안 다음, 적절하게 dialog 호출
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
				dialogBuilder.setView(layoutView);

				dialogBuilder.setPositiveButton("카메라로 찾기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});
				dialogBuilder.setNegativeButton("지도로 찾기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});

				final AlertDialog built = dialogBuilder.create();
				closeDialog.setOnClickListener(new Button.OnClickListener () {

					@Override
					public void onClick(View v) {
						built.dismiss();

					}

				});
				built.show();
			}
			else
			{
				// 라벨의 데이터에 따라 아이템의 종류를 안 다음, 적절하게 dialog 호출
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
				dialogBuilder.setView(layoutView);

				dialogBuilder.setPositiveButton("사용하기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});

				final AlertDialog built = dialogBuilder.create();
				closeDialog.setOnClickListener(new Button.OnClickListener () {

					@Override
					public void onClick(View v) {
						built.dismiss();

					}

				});
				built.show();
			}

		}
		else if ( v.getId() == R.id.item2Icn )
		{
			// 팝업 창 띄우기
			TextView lbl = (TextView) findViewById(R.id.item2Lbl);
			boolean itemExist = true;
			View layoutView = LayoutInflater.from(this).inflate(R.layout.dialog_item_use, null);

			final TextView itemName = (TextView) layoutView.findViewById(R.id.itemNameLbl);
			final TextView itemDesc = (TextView) layoutView.findViewById(R.id.itemDescLbl);
			final ImageView itemImg = (ImageView) layoutView.findViewById(R.id.itemImgView);
			final Button closeDialog = (Button) layoutView.findViewById(R.id.exitBtn);

			itemName.setText("무적");
			itemDesc.setText("몇 초간 무적이다");

			if ( !itemExist )
			{
				// 라벨의 데이터에 따라 아이템의 종류를 안 다음, 적절하게 dialog 호출
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
				dialogBuilder.setView(layoutView);

				dialogBuilder.setPositiveButton("카메라로 찾기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});
				dialogBuilder.setNegativeButton("지도로 찾기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});

				final AlertDialog built = dialogBuilder.create();
				closeDialog.setOnClickListener(new Button.OnClickListener () {

					@Override
					public void onClick(View v) {
						built.dismiss();

					}

				});
				built.show();
			}
			else
			{
				// 라벨의 데이터에 따라 아이템의 종류를 안 다음, 적절하게 dialog 호출
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
				dialogBuilder.setView(layoutView);

				dialogBuilder.setPositiveButton("사용하기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});

				final AlertDialog built = dialogBuilder.create();
				closeDialog.setOnClickListener(new Button.OnClickListener () {

					@Override
					public void onClick(View v) {
						built.dismiss();

					}

				});
				built.show();
			}

		}
		else if ( v.getId() == R.id.item3Icn )
		{
			// 팝업 창 띄우기
			TextView lbl = (TextView) findViewById(R.id.item3Lbl);
			boolean itemExist = true;
			View layoutView = LayoutInflater.from(this).inflate(R.layout.dialog_item_use, null);

			final TextView itemName = (TextView) layoutView.findViewById(R.id.itemNameLbl);
			final TextView itemDesc = (TextView) layoutView.findViewById(R.id.itemDescLbl);
			final ImageView itemImg = (ImageView) layoutView.findViewById(R.id.itemImgView);
			final Button closeDialog = (Button) layoutView.findViewById(R.id.exitBtn);

			itemName.setText("지도");
			itemDesc.setText("이거슨  지도이다");

			if ( !itemExist )
			{
				// 라벨의 데이터에 따라 아이템의 종류를 안 다음, 적절하게 dialog 호출
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
				dialogBuilder.setView(layoutView);

				dialogBuilder.setPositiveButton("카메라로 찾기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});
				dialogBuilder.setNegativeButton("지도로 찾기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});

				AlertDialog built = dialogBuilder.create();
				built.show();
			}
			else
			{
				// 라벨의 데이터에 따라 아이템의 종류를 안 다음, 적절하게 dialog 호출
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
				dialogBuilder.setView(layoutView);

				dialogBuilder.setPositiveButton("사용하기", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}

				});

				final AlertDialog built = dialogBuilder.create();
				closeDialog.setOnClickListener(new Button.OnClickListener () {

					@Override
					public void onClick(View v) {
						built.dismiss();

					}

				});
				built.show();
			}

		}
	}

	public void onLocationChange (Location l)
	{
		Log.d("verbose", "onlocationchange called");

		// 우선 방의 정보와 자신의 정보를 가져오기
		final User me;

		Team t1 = MakeRoomActivity.roomInfo.getTeam(0);
		Team t2 = MakeRoomActivity.roomInfo.getTeam(1);

		BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
		String mac = BTAdapter.getAddress();

		if ( t1.get(mac) != null )
		{
			me = t1.get(mac);
			me.setLatitude(l.getLatitude());
			me.setLongitude(l.getLongitude());
		}
		else
		{
			me = t2.get(mac);
			me.setLatitude(l.getLatitude());
			me.setLongitude(l.getLongitude());
		}

		// Asynctask 생성 후 서버로 보내기

		class SendLocationTask extends AsyncTask <Void, Void, RoomInfo>
		{			
			@Override
			protected RoomInfo doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				RoomInfo info = null;

				NetworkMethod toServ = NetworkMethodFactory.getInstance();
				try {
					info= toServ.emitEvent(NetworkMethod.UPDATE_POS, me);
				} catch (NetworkMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return info;
			}

			@Override
			protected void onPostExecute(RoomInfo result) {
				if ( result != null )
				{
					MakeRoomActivity.roomInfo = result;
				}
				else
				{
					Log.d("error", "server returned null on update_pos");
				}
			}

		}
		
		SendLocationTask task = new SendLocationTask();
		task.execute();
	}
}
