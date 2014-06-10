package com.JSCorp.wp;

import com.JSCorp.wp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BracketsFragment extends Fragment implements View.OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_brackets, container, false);
		
		Button dynamicBracketButton = (Button) rootView.findViewById(R.id.dynamicBracketButton);
		dynamicBracketButton.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		 //do what you want to do when button is clicked
	    switch (v.getId()) {
	        case R.id.dynamicBracketButton:
	        	Log.i("onClick", "Dynamic Bracket Activity");
	        	Intent dynamicBracketActivity = new Intent(getActivity(), DynamicBracketActivity.class);
	        	getActivity().startActivity(dynamicBracketActivity); 
	        	break;
	        case R.id.staticBracketButton:
	        	Log.i("onClick", "Static Bracket Activity");
	        	Intent staticBracketActivity = new Intent(getActivity(), StaticBracketActivity.class);
	        	getActivity().startActivity(staticBracketActivity); 
	        	break;
	    }
		
	}
}
