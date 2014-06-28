package com.JSCorp.wp;

import java.io.UnsupportedEncodingException;

import com.JSCorp.wp.EditTwitterActivity.SetUserTagInfo;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.app.ActionBar;
import android.app.Activity;
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
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EditFacebookActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_facebook);

		// Enabling Up / Back navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);

		((TextView) this.findViewById(R.id.callForFacebook))
				.setText(GlobalVars.user.getFacebook());

		ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleFacebook);
		if ("Y".equals(GlobalVars.user.getIs_facebook_visible())) {
			toggle.setChecked(true);
		} else {
			toggle.setChecked(false);
		}

		Button confirm_button = (Button) findViewById(R.id.facebook_confirm_button);
		confirm_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				updateFacebook();
			}

		});

		toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					// The toggle is enabled
					FPUser user = new FPUser();
					user.setDevice_id(GlobalVars.user.getDevice_id());
					user.setIs_facebook_visible("Y");
					new SetUserTagInfo().doInBackground(user);
					GlobalVars.user.setIs_facebook_visible("Y");
				} else {
					// The toggle is disabled
					FPUser user = new FPUser();
					user.setDevice_id(GlobalVars.user.getDevice_id());
					user.setIs_facebook_visible("N");
					new SetUserTagInfo().doInBackground(user);
					GlobalVars.user.setIs_facebook_visible("N");
				}
			}
		});

	}

	public void updateFacebook() {
		Toast.makeText(getApplicationContext(), "페이스북 정보가 저장 되었습니다.",
				Toast.LENGTH_SHORT).show();

		String facebookr_new = (String) ((TextView) this
				.findViewById(R.id.callForFacebook)).getText().toString();

		FPUser user = new FPUser();
		user.setDevice_id(GlobalVars.user.getDevice_id());
		user.setFacebook(facebookr_new);
		new SetUserTagInfo().doInBackground(user);

		System.out.println("Set new Twitter");
		GlobalVars.user.setFacebook(facebookr_new);

		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_prediction_actions, menu);

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
			updateFacebook();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class SetUserTagInfo extends AsyncTask {
		FPUser user;

		@Override
		protected Object doInBackground(Object... arg0) {
			FPUser user = (FPUser) arg0[0];
			this.user = user;
			// TODO Auto-generated method stub
			StrictMode.enableDefaults();
			System.out.println("UPDATE USER TAG INFO!!");
			// GameService.setGameProphet(gameProphet);
			try {
				UserService.updateUser(user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("MATCH SIZE:"+matches.size());
			return null;
		}
	}
}
