package org.human.gulim.runcatch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
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

				AlertDialog built = dialogBuilder.create();
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

				AlertDialog built = dialogBuilder.create();
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

				AlertDialog built = dialogBuilder.create();
				built.show();
			}

		}
	}
}
