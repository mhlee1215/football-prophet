package com.JSCorp.wp;

import java.util.ArrayList;
import java.util.List;

import com.JSCorp.wp.R;
import com.JSCorp.wp.adapter.RankListAdapter;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

public class RanksFragment extends Fragment {

	private RankListAdapter listAdapter; 
	List<FPUser> userTopRanks;
	List<FPUser> userRanks;
	List<FPUser> userRanksAll;
	
	
	View rootView;
	boolean isCreated = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.activity_dynamic_rank, container, false);
		
		
        
		
		
		
		isCreated = true;
		
		init();
		
		return rootView;
	}
	
	public void init(){
		System.out.println("ini!");
		if(isCreated)
			new GetRanks().doInBackground(this);
	}
	
	 
	
	public void doPrint(){
		Log.i(GlobalVars.WP_INFO_TAG, "Print ranks");
		System.out.println("userTopRanks: "+userTopRanks);
		System.out.println("userRanks :" +userRanks);
		listAdapter = new RankListAdapter(getActivity(), R.layout.fragment_dynamic_rank, userRanks);
		ListView listView = (ListView) rootView.findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
		
		if(userTopRanks.size() > 0){
			String nn = userTopRanks.get(0).getNickname();
			if(nn.length() <= 5) {
				((TextView)rootView.findViewById(R.id.firstPlaceNickname)).setTextSize(18); 
			} else {
				((TextView)rootView.findViewById(R.id.firstPlaceNickname)).setTextSize(14); 
			}
			((TextView)rootView.findViewById(R.id.firstPlaceNickname)).setText(nn);
			//((TextView)rootView.findViewById(R.id.firstPlaceTag1)).setVisibility(View.GONE);
			
			//((TextView)findViewById(R.id.firstPlaceTag1)).setText(userTopRanks.get(0).getTag());
		}
		
		if(userTopRanks.size() > 1){
			String nn = userTopRanks.get(1).getNickname();
			if(nn.length() <= 5) {
				((TextView)rootView.findViewById(R.id.secondPlaceNickname)).setTextSize(18); 
			} else {
				((TextView)rootView.findViewById(R.id.secondPlaceNickname)).setTextSize(14); 
			}
			((TextView)rootView.findViewById(R.id.secondPlaceNickname)).setText(nn);
			//((TextView)rootView.findViewById(R.id.secondPlaceTag1)).setVisibility(View.GONE);
			
			//((TextView)findViewById(R.id.secondPlaceTag1)).setText(userTopRanks.get(1).getTag());
		}
		
		if(userTopRanks.size() > 2){
			String nn = userTopRanks.get(2).getNickname();
			if(nn.length() <= 5) {
				((TextView)rootView.findViewById(R.id.thridPlaceNickname)).setTextSize(18); 
			} else {
				((TextView)rootView.findViewById(R.id.thridPlaceNickname)).setTextSize(14); 
			}
			((TextView)rootView.findViewById(R.id.thridPlaceNickname)).setText(nn);
			//((TextView)rootView.findViewById(R.id.thridPlaceTag1)).setVisibility(View.GONE);
			
			//((TextView)findViewById(R.id.thridPlaceTag1)).setText(userTopRanks.get(2).getTag());
		}
		
		if(userTopRanks.size() > 0) {
			TableLayout firstPlace = (TableLayout) rootView.findViewById(R.id.firstPlace);
			firstPlace.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listAdapter.detailInfo(0, userTopRanks);
				}
			});
	        }
		if(userTopRanks.size() > 1) {
			TableLayout secondPlace = (TableLayout) rootView.findViewById(R.id.secondPlace);
			secondPlace.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listAdapter.detailInfo(1, userTopRanks);
				}
			});
	        } 
		if(userTopRanks.size() > 2) {
			TableLayout thirdPlace = (TableLayout) rootView.findViewById(R.id.thirdPlace);
			thirdPlace.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listAdapter.detailInfo(2, userTopRanks);
				}
			});
	        }
		
		
		
		for(FPUser user : userRanksAll){
			if(user.getId() == GlobalVars.user.getId()){
				GlobalVars.user.setRank(user.getRank());
				break;
			}
		}
		
		String rankStr = "-위"; 
		if(GlobalVars.user.getRank() > 0)
			rankStr = Integer.toString(GlobalVars.user.getRank())+"위";
		TextView myRank = (TextView) rootView.findViewById(R.id.myRank);
		myRank.setText(rankStr);
		TextView myNickname = (TextView) rootView.findViewById(R.id.myNickname);
		myNickname.setText(GlobalVars.user.getNickname());
		TextView tag = (TextView) rootView.findViewById(R.id.tag);
		tag.setText(GlobalVars.user.getTag());
		
		
		/*
		if(GlobalVars.dynamicBracketDialog.isShowing())
			GlobalVars.dynamicBracketDialog.cancel();
		 */
	}
	
	
	public class GetRanks extends AsyncTask {

		RanksFragment tContext;
		List<FPUser> userRanks;
		@Override
		protected Object doInBackground(Object... arg0) {
			tContext = (RanksFragment) arg0[0];
			// TODO Auto-generated method stub
			StrictMode.enableDefaults();
			userRanks = UserService.getRankingUsers();
			//System.out.println("userRanks:"+userRanks);
			
			tContext.userRanksAll = userRanks;
			if(userRanks == null) {
				tContext.userTopRanks = new ArrayList<FPUser>();
				tContext.userRanks = new ArrayList<FPUser>();
			} else if(userRanks.size() < 3) {
				tContext.userTopRanks = userRanks;
				tContext.userRanks = new ArrayList<FPUser>();
			} else {
				tContext.userTopRanks = userRanks.subList(0, 3);
				tContext.userRanks = userRanks.subList(3, userRanks.size());
			}
			//tContext.userRanks = userRanks;
			tContext.doPrint();
			
			return null;
		}	
	}
}
