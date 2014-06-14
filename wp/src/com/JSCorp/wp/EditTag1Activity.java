package com.JSCorp.wp;

import java.io.UnsupportedEncodingException;

import com.JSCorp.wp.EditNicknameActivity.SetUserInfo;
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
import android.widget.TextView;
import android.widget.Toast;

public class EditTag1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tag1);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        ((TextView) this.findViewById(R.id.callForTag1)).setText(GlobalVars.user.getTag());
		
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
            //save user decisions to DB.
        	//go back to game list view.
        	
        	String tag_new = (String) ((TextView) this.findViewById(R.id.callForTag1)).getText().toString();
        	
        	if(tag_new.length() > 20){
          		Toast.makeText(getApplicationContext(),
            		      "내 한마디는 20글자 미만으로 입력해주세요.", Toast.LENGTH_SHORT).show();
          		return true;
          	}
        	
        	FPUser user = new FPUser();
          	user.setDevice_id(GlobalVars.user.getDevice_id());
          	user.setTag(tag_new);
          	new SetUserTagInfo().doInBackground(user);
          	
          	System.out.println("Set new tag"); 
  			GlobalVars.user.setTag(tag_new);
        	
        	super.onBackPressed();
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
			//GameService.setGameProphet(gameProphet);
			try {
				UserService.updateUser(user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("MATCH SIZE:"+matches.size());
			return null;
		}
	}
}
