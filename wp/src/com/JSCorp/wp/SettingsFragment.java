package com.JSCorp.wp;

import com.JSCorp.wp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsFragment extends Fragment implements View.OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

		Button userProfileButton = (Button) rootView.findViewById(R.id.userProfileButton);
		userProfileButton.setOnClickListener(this);
		Button tutorialButton = (Button) rootView.findViewById(R.id.tutorialButton);
		tutorialButton.setOnClickListener(this);
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		 //do what you want to do when button is clicked
	    switch (v.getId()) {
	        case R.id.userProfileButton:
	        	Log.i("onClick", "User Profile Activity");
	        	Intent userProfileActivity = new Intent(getActivity(), UserProfileActivity.class);
	        	getActivity().startActivity(userProfileActivity); 
	        	break;
	        case R.id.tutorialButton:
	        	Log.i("onClick", "Tutorial Activity");
	        	Intent tutorialActivity = new Intent(getActivity(), UserProfileActivity.class);
	        	getActivity().startActivity(tutorialActivity); 
	        	break;
	    }
	}
}
