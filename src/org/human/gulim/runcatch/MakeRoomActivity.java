package org.human.gulim.runcatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MakeRoomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_room);
	}
	
	public void makeRoom (View v)
	{
		// 적절한 처리를 해서 방을 만든다
		
		// 다음 화면으로
		
		Intent intent = new Intent (this, WaitUserActivity.class);
		startActivity(intent);
		
		finish();
	}
}
