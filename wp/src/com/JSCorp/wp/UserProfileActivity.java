package com.JSCorp.wp;

import com.JSCorp.wp.adapter.RankListAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		
		TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tManager.getDeviceId();
		System.out.println("device Id :"+deviceId);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView nickname = (TextView) findViewById(R.id.nickname);
		nickname.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Nickname Activity");
	        	Intent editNicknameActivity = new Intent(UserProfileActivity.this, EditNicknameActivity.class);
	        	startActivity(editNicknameActivity); 
			}
		});
		
		TextView tag1 = (TextView) findViewById(R.id.tag1);
		tag1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Tag1 Activity");
	        	Intent editTag1Activity = new Intent(UserProfileActivity.this, EditTag1Activity.class);
	        	startActivity(editTag1Activity); 
			}
		});

		TextView facebook = (TextView) findViewById(R.id.facebook);
		facebook.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Facebook Activity");
	        	Intent editFacebookActivity = new Intent(UserProfileActivity.this, EditFacebookActivity.class);
	        	startActivity(editFacebookActivity); 
			}
		});
		
		TextView twitter = (TextView) findViewById(R.id.twitter);
		twitter.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Twitter Activity");
	        	Intent editTwitterActivity = new Intent(UserProfileActivity.this, EditTwitterActivity.class);
	        	startActivity(editTwitterActivity); 
			}
		});
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
