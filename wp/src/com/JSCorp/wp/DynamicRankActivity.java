package com.JSCorp.wp;

import java.util.List;

import com.JSCorp.wp.adapter.PredictionListAdapter;
import com.JSCorp.wp.adapter.RankListAdapter;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;


public class DynamicRankActivity extends ListActivity {	
	
	private RankListAdapter listAdapter; 
	List<FPUser> userRanks;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_rank);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
//		String[] lista = getResources().getStringArray(R.array.nation);
//		
//		listAdapter = new RankListAdapter(this, R.layout.fragment_dynamic_rank, lista);
//		ListView listView = (ListView) findViewById(android.R.id.list);
//		listView.setAdapter(listAdapter);
		
        new GetRanks().doInBackground(this);
        
		
		TableLayout firstPlace = (TableLayout)findViewById(R.id.firstPlace);
		TableLayout secondPlace = (TableLayout)findViewById(R.id.secondPlace);
		TableLayout thirdPlace = (TableLayout)findViewById(R.id.thirdPlace);
		firstPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(1);
			}
		});
		secondPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(1);
			}
		});
		thirdPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(1);
			}
		});
		 
		 
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
        	//FragmentManager fm = getActivity().getSupportFragmentManager();
            //fm.popBackStack();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	
	public void doPrint(){
		Log.i(GlobalVars.WP_INFO_TAG, "Print matches");
		System.out.println(userRanks);
		listAdapter = new RankListAdapter(this, R.layout.fragment_dynamic_rank, userRanks);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
		
		//if(GlobalVars.dynamicBracketDialog.isShowing())
		//	GlobalVars.dynamicBracketDialog.cancel();
	}
	
	
	public class GetRanks extends AsyncTask {

		DynamicRankActivity tContext;
		List<FPUser> userRanks;
		@Override
		protected Object doInBackground(Object... arg0) {
			tContext = (DynamicRankActivity) arg0[0];
			// TODO Auto-generated method stub
			userRanks = UserService.getRankingUsers();
			//System.out.println("MATCH SIZE:"+matches.size());
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tContext.userRanks = userRanks;
			tContext.doPrint();
		}
		
	}
}
