package com.JSCorp.wp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class EditNicknameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_nickname);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
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
        	Toast.makeText(getApplicationContext(),
        		      "Need to Implement Data Save", Toast.LENGTH_SHORT).show();
        	super.onBackPressed();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
