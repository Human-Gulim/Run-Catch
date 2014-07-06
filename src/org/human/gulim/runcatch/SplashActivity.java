package org.human.gulim.runcatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	private AnimationDrawable frameAnimation;
	private ImageView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// 컨트롤 ImageView 객체를 가져온다
		view = (ImageView) findViewById(R.id.logoView);

		// animation_list.xml 를 ImageView 백그라운드에 셋팅한다
		view.setBackgroundResource(R.drawable.logo_animation);

		// 이미지를 동작시키기위해  AnimationDrawable 객체를 가져온다.
		frameAnimation = (AnimationDrawable) view.getBackground();
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		  @Override
		  public void run() {
			
		    Intent intent = new Intent (getApplicationContext(), MainActivity.class);
		    startActivity(intent);
		    
		    finish();
		  }
		}, (1000 * 5) );
	}

	// 어플에 포커스가 가면 동작한다
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			// 어플에 포커스가 갈때 시작된다
			frameAnimation.start();
		} else {
			// 어플에 포커스를 떠나면 종료한다
			frameAnimation.stop();
		}
	}

}
