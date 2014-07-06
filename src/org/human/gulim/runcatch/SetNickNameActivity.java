package org.human.gulim.runcatch;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class SetNickNameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_nick_name);

	}

	public void nickSubmit (View v)
	{
		// 닉네임의 데이터를 체크 한 후, SharedPreference에 저장
		EditText inputField = (EditText) findViewById(R.id.editText1);
		
		if ( inputField.getText().toString().equals("") )
		{
			// 닉네임이 비어있으므로 올바르지 않음
			Toast.makeText(getApplicationContext(), "닉네임이 올바르지 않습니다. 올바른 닉네임을 입력 해 주세요!",
					Toast.LENGTH_LONG).show();
			return;
		}
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor ed = prefs.edit();
	
		ed.putString("nickName", inputField.getText().toString());
		ed.commit();
		
		finish();
	}
}
