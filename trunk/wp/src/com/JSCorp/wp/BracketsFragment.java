package com.JSCorp.wp;

import java.util.List;

import com.JSCorp.wp.R;
import com.JSCorp.wp.adapter.PredictionListAdapter;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.var.GlobalVars;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class BracketsFragment extends Fragment {

	Context context;
	
	////
	public List<FPGameMatchSchedule> matches;
	ProgressDialog dialog;
	PredictionListAdapter listAdapter;

	public BracketsFragment() {
		// TODO Auto-generated constructor stub
	}

	public BracketsFragment(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_dynamic_bracket, container,
				false);
		
		//// 
        if(GlobalVars.matches == null){
        	Log.i(GlobalVars.WP_INFO_TAG, "Transaction to server for match retrieval.");
        	
        	/*
        	ProgressDialog dialog = new ProgressDialog(context);
    		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    		dialog.setTitle("Waiting..");
    		dialog.setMessage("Now retrieving game info");
    		dialog.setProgress(0);
    		dialog.setMax(100);
    		dialog.setButton("취소", new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int which) {
    				dialog.cancel();
    			}
    		});
    		dialog.show();

    		GlobalVars.dynamicBracketDialog = dialog;
        	*/
        	
        	new GetGameTeamMap().execute(this);
        }
        else{
        	Log.i(GlobalVars.WP_INFO_TAG, "Match found in the app. Skip match retrieval to server.");
        	this.matches = GlobalVars.matches;
        	doPrint();
        }
        ////
        
        return rootView;
	}
	
	public void doPrint(){
		Log.i(GlobalVars.WP_INFO_TAG, "Print matches");
		//Temporally hid tournamnet
		matches = matches.subList(0, 48);
		System.out.println("MATCH LIST: "+matches);
		listAdapter = new PredictionListAdapter(this.context, R.layout.fragment_dynamic_bracket, matches);
		ListView listView = (ListView) getView().findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
		
		/*
		if(GlobalVars.dynamicBracketDialog.isShowing())
			GlobalVars.dynamicBracketDialog.cancel();
		*/
	}
	
	public class GetGameTeamMap extends AsyncTask {

		BracketsFragment tContext;
		List<FPGameMatchSchedule> matches;
		@Override
		protected Object doInBackground(Object... arg0) {
			
			tContext = (BracketsFragment) arg0[0];
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
	
	/*
	public class StartChildActivity extends AsyncTask {

		
		@Override
		protected Object doInBackground(Object... arg0) {
			
			ProgressDialog dialog = new ProgressDialog((Context) arg0[0]);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setTitle("Waiting..");
			dialog.setMessage("Now retrieving game info");
			dialog.setProgress(0);
			dialog.setMax(100);
			dialog.setButton("취소", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			dialog.show();

			GlobalVars.dynamicBracketDialog = dialog;

			Log.i("onClick", "Dynamic Bracket Activity");
			Intent dynamicBracketActivity = new Intent(getActivity(),
					(Class<?>) arg0[1]);
			getActivity().startActivity(dynamicBracketActivity);
			
			return null;
		}
	}
	*/
}
