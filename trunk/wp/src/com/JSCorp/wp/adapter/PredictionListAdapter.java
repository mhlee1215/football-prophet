package com.JSCorp.wp.adapter;

import java.util.List;

import com.JSCorp.wp.DynamicBracketActivity;
import com.JSCorp.wp.DynamicPredictionActivity;
import com.JSCorp.wp.R;
import com.JSCorp.wp.domain.FPGameMatchSchedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PredictionListAdapter extends BaseAdapter {
	
	LayoutInflater inflater;
	String[] categorya;
	List<FPGameMatchSchedule> matches;
	Context mContext;
	int mListLayout;
	public String TAG = "listAdapter";
	public int listCount = 0;
	
	public PredictionListAdapter(Context tContext, int listLayout, String[] tmpa) {
		mContext = tContext;
		mListLayout = listLayout;
		categorya = tmpa;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(categorya != null) {
			listCount = categorya.length;
		}
	}
	
	public PredictionListAdapter(Context tContext, int listLayout, List<FPGameMatchSchedule> tmpa) {
		mContext = tContext;
		mListLayout = listLayout;
		matches = tmpa;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(matches != null) {
			listCount = matches.size();
		}
	}
	
	@Override
	public int getCount() {
		return listCount;
	}
	
	@Override
	public Object getItem(int arg0) {
		return arg0;
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = inflater.inflate(mListLayout,  parent, false);
		}
		final int positionInt  = position;
		
		
		//((TextView) convertView.findViewById(R.id.matchTime)).setText(categorya[position]);
		//((TextView) convertView.findViewById(R.id.matchResult)).setText(categorya[position]);
		//((TextView) convertView.findViewById(R.id.myPrediction)).setText(categorya[position]);
		//((ImageView) convertView.findViewById(R.id.predictionResult)).setImage();
		
		//Visibility Setting Cases
		//Default (nations, nextGameTimer/Timer)
		//System.out.println("Pos:"+position+", "+matches.get(position));
		((TextView) convertView.findViewById(R.id.nations_home)).setText(matches.get(position).getHome_team_name());
		((TextView) convertView.findViewById(R.id.nations_away)).setText(matches.get(position).getAway_team_name());
		((TextView) convertView.findViewById(R.id.matchTime)).setText(matches.get(position).getReference_time());
		
		//((TextView) convertView.findViewById(R.id.nations_home)).setText(categorya[position]);
		//((TextView) convertView.findViewById(R.id.nations_away)).setText(categorya[position]);
		((TextView) convertView.findViewById(R.id.matchResult)).setVisibility(View.INVISIBLE);
		((TextView) convertView.findViewById(R.id.myPrediction)).setVisibility(View.INVISIBLE);
		(convertView.findViewById(R.id.predictionResult)).setVisibility(View.INVISIBLE);
		
		//Default + prediction (nations, nextGameTimer/Timer, myPrediction)
		
		//Ended (nations, matchResult, predictionREsult)
		//Ended game/On going game: set to uneditable
		
		
		convertView.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				detailInfo(positionInt);
			}
		});
		
		return convertView;
	}
	
	public void detailInfo(int position) {
		
		final Dialog dialog = new Dialog(mContext);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    dialog.setContentView(R.layout.prediction_dialog);

	    // set the custom dialog components - text and button
	    //TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
	    //TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);

	    ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.imageButton1);
	    // if button is clicked, close the custom dialog
	    dialogButton.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();

	        }
	    }); 
	    
	    Button predictionButton = (Button) dialog.findViewById(R.id.btnPredict);
	    // if button is clicked, close the custom dialog
	    predictionButton.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            Intent dynamicPredictionActivity = new Intent(mContext, DynamicPredictionActivity.class);
	        	mContext.startActivity(dynamicPredictionActivity); 
	        }
	    }); 
	    
	    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	    dialog.show();
	}

}
