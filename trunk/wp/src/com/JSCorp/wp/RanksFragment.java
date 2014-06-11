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

public class RanksFragment extends Fragment implements View.OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_ranks, container, false);

		Button dynamicRankButton = (Button) rootView.findViewById(R.id.dynamicRankButton);
		dynamicRankButton.setOnClickListener(this);
		Button staticRankButton = (Button) rootView.findViewById(R.id.staticRankButton);
		staticRankButton.setOnClickListener(this);
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		 //do what you want to do when button is clicked
	    switch (v.getId()) {
	        case R.id.dynamicRankButton:
	        	Log.i("onClick", "Dynamic Rank Activity");
	        	Intent dynamicRankActivity = new Intent(getActivity(), DynamicRankActivity.class);
	        	getActivity().startActivity(dynamicRankActivity); 
	        	break;
	        case R.id.staticRankButton:
	        	Log.i("onClick", "Static Rank Activity");
	        	Intent staticBracketActivity = new Intent(getActivity(), DynamicRankActivity.class);
	        	getActivity().startActivity(staticBracketActivity); 
	        	break;
	    }
	}
}
