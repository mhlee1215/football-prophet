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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;


public class DynamicRankActivity extends ListActivity {	
	
	private RankListAdapter listAdapter; 
	
	List<FPUser> userTopRanks;
	List<FPUser> userRanks;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_rank);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
//		String[] lista = getResources().getStringArray(R.array.nation);
//		listAdapter = new RankListAdapter(this, R.layout.fragment_dynamic_rank, lista);
//		ListView listView = (ListView) findViewById(android.R.id.list);
//		listView.setAdapter(listAdapter);
        
        ProgressDialog dialog = new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setTitle("Waiting..");
		dialog.setMessage("Now retrieving Ranking info");
		dialog.setProgress(0);
		dialog.setMax(100);
		dialog.setButton("취소", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		dialog.show();

		GlobalVars.dynamicBracketDialog = dialog;
		
        new GetRanks().doInBackground(this);
        
		TableLayout firstPlace = (TableLayout)findViewById(R.id.firstPlace);
		TableLayout secondPlace = (TableLayout)findViewById(R.id.secondPlace);
		TableLayout thirdPlace = (TableLayout)findViewById(R.id.thirdPlace);
		firstPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(0, userTopRanks);
			}
		});
		secondPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(1, userTopRanks);
			}
		});
		thirdPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(2, userTopRanks);
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
		Log.i(GlobalVars.WP_INFO_TAG, "Print ranks");
		System.out.println("userTopRanks: "+userTopRanks);
		System.out.println("userRanks :" +userRanks);
		listAdapter = new RankListAdapter(this, R.layout.fragment_dynamic_rank, userRanks);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
		
		if(userTopRanks.size() > 0){
			String nn = userTopRanks.get(0).getNickname();
			if(nn.length() <= 5) {
				((TextView)findViewById(R.id.firstPlaceNickname)).setTextSize(12); 
			} else {
				((TextView)findViewById(R.id.firstPlaceNickname)).setTextSize(12); 
			}
			((TextView)findViewById(R.id.firstPlaceNickname)).setText(nn);
			//((TextView)findViewById(R.id.firstPlaceTag1)).setText(userTopRanks.get(0).getTag());
		}
		
		if(userTopRanks.size() > 1){
			String nn = userTopRanks.get(0).getNickname();
			if(nn.length() <= 5) {
				((TextView)findViewById(R.id.secondPlaceNickname)).setTextSize(12); 
			} else {
				((TextView)findViewById(R.id.secondPlaceNickname)).setTextSize(12); 
			}
			((TextView)findViewById(R.id.secondPlaceNickname)).setText(nn);
			//((TextView)findViewById(R.id.secondPlaceTag1)).setText(userTopRanks.get(1).getTag());
		}
		
		if(userTopRanks.size() > 2){
			String nn = userTopRanks.get(0).getNickname();
			if(nn.length() <= 5) {
				((TextView)findViewById(R.id.thridPlaceNickname)).setTextSize(12); 
			} else {
				((TextView)findViewById(R.id.thridPlaceNickname)).setTextSize(12); 
			}
			((TextView)findViewById(R.id.thridPlaceNickname)).setText(nn);
			//((TextView)findViewById(R.id.thridPlaceTag1)).setText(userTopRanks.get(2).getTag());
		}
		
		
		
		if(GlobalVars.dynamicBracketDialog.isShowing())
			GlobalVars.dynamicBracketDialog.cancel();
	}
	
	
	public class GetRanks extends AsyncTask {

		DynamicRankActivity tContext;
		List<FPUser> userRanks;
		@Override
		protected Object doInBackground(Object... arg0) {
			tContext = (DynamicRankActivity) arg0[0];
			// TODO Auto-generated method stub
			StrictMode.enableDefaults();
			userRanks = UserService.getRankingUsers();
			//System.out.println("userRanks:"+userRanks);
			
			tContext.userTopRanks = userRanks.subList(0, 3);
			tContext.userRanks = userRanks.subList(3, userRanks.size());
			//tContext.userRanks = userRanks;
			tContext.doPrint();
			
			return null;
		}	
	}
}
