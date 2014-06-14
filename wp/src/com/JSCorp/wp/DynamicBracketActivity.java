package com.JSCorp.wp;

import java.util.Arrays;
import java.util.List;

import com.JSCorp.wp.adapter.PredictionListAdapter;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.var.GlobalVars;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DynamicBracketActivity extends ListActivity {

	public List<FPGameMatchSchedule> matches;
	ProgressDialog dialog;
	PredictionListAdapter listAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_bracket);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        if(GlobalVars.matches == null){
        	Log.i(GlobalVars.WP_INFO_TAG, "Transaction to server for match retrieval.");
        	new GetGameTeamMap().execute(this);
        }
        else{
        	Log.i(GlobalVars.WP_INFO_TAG, "Match found in the app. Skip match retrieval to server.");
        	this.matches = GlobalVars.matches;
        	doPrint();
        }
        	
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
	
	public void doPrint(){
		Log.i(GlobalVars.WP_INFO_TAG, "Print matches");
		System.out.println(matches);
		listAdapter = new PredictionListAdapter(this, R.layout.fragment_dynamic_bracket, matches);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
		
		if(GlobalVars.dynamicBracketDialog.isShowing())
			GlobalVars.dynamicBracketDialog.cancel();
	}
	
	
	public class GetGameTeamMap extends AsyncTask {

		DynamicBracketActivity tContext;
		List<FPGameMatchSchedule> matches;
		@Override
		protected Object doInBackground(Object... arg0) {
			tContext = (DynamicBracketActivity) arg0[0];
			// TODO Auto-generated method stub
			System.out.println(GlobalVars.user);
			matches = GameService.getGameMatchSchedules(GlobalVars.user.getId());
			//System.out.println("MATCH SIZE:"+matches.size());
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tContext.matches = matches;
			tContext.doPrint();
		}
		
	}
}
