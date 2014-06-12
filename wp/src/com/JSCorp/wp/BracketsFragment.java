package com.JSCorp.wp;

import java.util.List;

import com.JSCorp.wp.R;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.var.GlobalVars;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("ValidFragment")
public class BracketsFragment extends Fragment implements View.OnClickListener {

	Context context;

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

		View rootView = inflater.inflate(R.layout.fragment_brackets, container,
				false);

		Button dynamicBracketButton = (Button) rootView
				.findViewById(R.id.dynamicBracketButton);
		dynamicBracketButton.setOnClickListener(this);
		Button staticBracketButton = (Button) rootView
				.findViewById(R.id.staticBracketButton);
		staticBracketButton.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// do what you want to do when button is clicked
		switch (v.getId()) {
		case R.id.dynamicBracketButton:

			new StartChildActivity().doInBackground(this.context, DynamicBracketActivity.class);
			
			break;
		case R.id.staticBracketButton:
			Log.i("onClick", "Static Bracket Activity");
			Intent staticBracketActivity = new Intent(getActivity(),
					StaticBracketActivity.class);
			getActivity().startActivity(staticBracketActivity);
			break;
		}

	}
	
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
}
