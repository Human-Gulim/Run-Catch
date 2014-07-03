package org.human.gulim.runcatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void makeRoom (View v)
	{
		// 방을 만드는 Activity 실행
		finish();
	}
	
	public void joinRoom (View v)
	{
		// 방에 참가하는 Activity 실행
		finish();
	}
}
