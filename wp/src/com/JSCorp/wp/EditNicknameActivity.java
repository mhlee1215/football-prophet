package com.JSCorp.wp;

import java.io.UnsupportedEncodingException;

import com.JSCorp.wp.domain.FPGameProphet;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EditNicknameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_nickname);

		// Enabling Up / Back navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);

		((TextView) this.findViewById(R.id.callForNickname))
				.setText(GlobalVars.user.getNickname());

		if ("Y".equals(GlobalVars.user.getIs_nickname_initialized())) {
			// Remove Action bar accept button.
			((TextView) this.findViewById(R.id.callForNickname))
					.setEnabled(false);
			((TextView) this.findViewById(R.id.nicknameText)).setText("내 닉네임:");
			
			Button confirm_button = (Button) findViewById(R.id.nickname_confirm_button);
			confirm_button.setVisibility(View.INVISIBLE);
		}else{
			Bundle b = getIntent().getExtras();
			String isAfterSplash = b.getString("isAfterSplash");

			System.out.println("isAfterSplash:" + isAfterSplash);
			
			Button confirm_button = (Button) findViewById(R.id.nickname_confirm_button);
			confirm_button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					updateNickname();
				}
				
			});
		}

		

	}
	
	public void updateNickname(){
		if ("Y".equals(GlobalVars.user.getIs_nickname_initialized())) {
			System.out.println("CANNOT edit nick name, already set.");
			Toast.makeText(getApplicationContext(),
					"Cannot Update nick name. It already defined.",
					Toast.LENGTH_SHORT).show();

		} else if ("N".equals(GlobalVars.user.getIs_nickname_initialized())) {

			String nickname_new = (String) ((TextView) this
					.findViewById(R.id.callForNickname)).getText()
					.toString();

			if (nickname_new.length() < 4 || nickname_new.length() > 14) {
				Toast.makeText(getApplicationContext(),
						"닉네임을 4글자 이상, 14글자 미만으로 입력해주세요.",
						Toast.LENGTH_SHORT).show();
				//return true;
			}

			FPUser user = new FPUser();
			user.setDevice_id(GlobalVars.user.getDevice_id());
			user.setNickname(nickname_new);
			new SetUserInfo().doInBackground(user, this);

			System.out.println("Set new nick name");
			GlobalVars.user.setNickname(nickname_new);
			GlobalVars.user.setIs_nickname_initialized("Y");

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		if ("N".equals(GlobalVars.user.getIs_nickname_initialized())) {
			inflater.inflate(R.menu.activity_prediction_actions, menu);
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case android.R.id.home:
			super.onBackPressed();
			return true;
		case R.id.action_accept:
			// save user decisions to DB.
			// go back to game list view.

			updateNickname();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void doAfterAction() {
		
		Bundle b = getIntent().getExtras();
		String isAfterSplash = b.getString("isAfterSplash");
		if ("Y".equals(isAfterSplash)) {
			Intent i = new Intent(getBaseContext(), MainActivity.class);
			startActivity(i);
			// Remove activity
			finish();
		} else {
			super.onBackPressed();
		}
	}

	public class SetUserInfo extends AsyncTask {
		EditNicknameActivity context;
		FPUser user;

		@Override
		protected Object doInBackground(Object... arg0) {
			FPUser user = (FPUser) arg0[0];
			this.context = (EditNicknameActivity) arg0[1];
			this.user = user;
			// TODO Auto-generated method stub
			StrictMode.enableDefaults();
			System.out.println("UPDATE USER INFO!!");
			// GameService.setGameProphet(gameProphet);
			try {
				UserService.initializeUser(user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("MATCH SIZE:"+matches.size());

			Toast.makeText(getApplicationContext(), "닉네임이 수정되었습니다.",
					Toast.LENGTH_SHORT).show();
			
			context.doAfterAction();
			return user;
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//context.doAfterAction();
		}
	}

}
