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
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PredictionListAdapter extends BaseAdapter {
	
	int tempPredictValue = 0;
	
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
	    
	    if(tempPredictValue == 1) {
	    	((TextView) dialog.findViewById(R.id.btnPredict1)).setText("승리할거임");
	    } else if(tempPredictValue == 2) {
	    	((TextView) dialog.findViewById(R.id.btnPredict2)).setText("승리할거임");
	    } else if(tempPredictValue == 3) {
	    	((TextView) dialog.findViewById(R.id.btnPredict3)).setText("승리할거임");
	    }
	    
	    ((TextView)dialog.findViewById(R.id.matchGroup)).setText(matches.get(position).getGameGroup() + "조 조별매치");
	    
	    //System.out.println(matches);
	    String homeImage = "flag" + Integer.toString(matches.get(position).getHome_team_id());
	    String awayImage = "flag" + Integer.toString(matches.get(position).getAway_team_id());
	    int resIDHome = mContext.getResources().getIdentifier(homeImage, "drawable", mContext.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagA)).setImageResource(resIDHome );;
	    int resIDAway = mContext.getResources().getIdentifier(awayImage, "drawable", mContext.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagB)).setImageResource(resIDAway );;
	    
	    String nationHomeName = matches.get(position).getHome_team_name();
	    String nationAwayName = matches.get(position).getAway_team_name();
	    if(nationHomeName.equals("보스니아 헤르체고비나")) {
	    	nationHomeName = nationHomeName.replace(" ", "\n");
	    }
	    if(nationAwayName.equals("보스니아 헤르체고비나")) {
	    	nationAwayName = nationAwayName.replace(" ", "\n");
	    }
	    ((TextView) dialog.findViewById(R.id.nationA)).setText(nationHomeName);
	    ((TextView) dialog.findViewById(R.id.nationB)).setText(nationAwayName);
	    
	    //((TextView) dialog.findViewById(R.id.nationA)).setText(matches.get(position).get);

	    // if button is clicked, close the custom dialog
	    ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.imageButton1);
	    dialogButton.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();

	        }
	    }); 
	    
	    Button predictionButton1 = (Button) dialog.findViewById(R.id.btnPredict1);
	    predictionButton1.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            predictHomeVictory();
	        }
	    });
	    
	    Button predictionButton2 = (Button) dialog.findViewById(R.id.btnPredict2);
	    predictionButton2.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            predictDraw();
	        }
	    }); 
	    
	    Button predictionButton3 = (Button) dialog.findViewById(R.id.btnPredict3);
	    predictionButton3.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            predictAwayVictory();
	        }
	    }); 
	    
	    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	    dialog.show();
	}
	
	private void predictHomeVictory() {
		// TODO Auto-generated method stub
		Log.i("onClick", "1");
		tempPredictValue = 1;
		
	}
	private void predictDraw() {
		// TODO Auto-generated method stub
		Log.i("onClick", "2");
		tempPredictValue = 2;
	}
	private void predictAwayVictory() {
		// TODO Auto-generated method stub
		Log.i("onClick", "3");
		tempPredictValue = 3;
	}

}
