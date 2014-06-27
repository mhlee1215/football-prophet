package com.JSCorp.wp;

import com.JSCorp.wp.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_user_profile, container, false);
		
		TelephonyManager tManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tManager.getDeviceId();
		System.out.println("device Id :"+deviceId);
		 
		TextView nickname = (TextView) rootView.findViewById(R.id.nickname);
		nickname.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Nickname Activity");
	        	Intent editNicknameActivity = new Intent(getActivity(), EditNicknameActivity.class);
	        	
	        	Bundle b = new Bundle();
     		    b.putString("isAfterSplash", "N"); //Your id
     		    editNicknameActivity.putExtras(b); //Put your id to your next Intent
	        	
	        	startActivity(editNicknameActivity);
	        	//Fragment fragment1 = (Fragment) ((MainActivity)getActivity()).getFragmentbyPosition(1);
	        	//((Object) fragment1).refreshFragment();
			}
		});
		
		TextView tag1 = (TextView) rootView.findViewById(R.id.tag1);
		tag1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Tag1 Activity");
	        	Intent editTag1Activity = new Intent(getActivity(), EditTag1Activity.class);
	        	startActivity(editTag1Activity); 
	        	//Intent editTag1Activity = new Intent(getActivity(), TournamentBracketActivity.class);
	        	//startActivity(editTag1Activity); 
			}
		});

		TextView facebook = (TextView) rootView.findViewById(R.id.facebook);
		facebook.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Facebook Activity");
	        	Intent editFacebookActivity = new Intent(getActivity(), EditFacebookActivity.class);
	        	startActivity(editFacebookActivity); 
			}
		});
		
		TextView twitter = (TextView) rootView.findViewById(R.id.twitter);
		twitter.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClick", "Edit Twitter Activity");
	        	Intent editTwitterActivity = new Intent(getActivity(), EditTwitterActivity.class);
	        	startActivity(editTwitterActivity); 
			}
		});
		
		return rootView;
	}
}
