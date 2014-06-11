package com.JSCorp.wp.adapter;

import com.JSCorp.wp.DynamicBracketActivity;
import com.JSCorp.wp.DynamicPredictionActivity;
import com.JSCorp.wp.R;

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
		((TextView) convertView.findViewById(R.id.nations)).setText(categorya[position]);
		((TextView) convertView.findViewById(R.id.matchResult)).setVisibility(View.INVISIBLE);
		((TextView) convertView.findViewById(R.id.myPrediction)).setVisibility(View.INVISIBLE);
		(convertView.findViewById(R.id.predictionResult)).setVisibility(View.INVISIBLE);
		
		//Default + 예측 (nations, nextGameTimer/Timer, myPrediction)
		
		//Ended (nations, matchResult, predictionREsult)
		//종료된 경기/경기중 경기 Edit 못하게 설정
		
		
		convertView.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				detailInfo(positionInt);
			}
		});
		
		return convertView;
	}
	
	public void detailInfo(int position) {
		/*
		//ContextThemeWrapper ctw = new ContextThemeWrapper(mContext, R.style.DialogSlideAnim);
		AlertDialog.Builder detailPop = new AlertDialog.Builder(mContext);
		detailPop.setMessage(categorya[position]).setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Toast.makeText(mContext,  "확인을 클릭 했습니다.", Toast.LENGTH_LONG).show();
			}
		}).setNegativeButton("닫기", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		
		AlertDialog alert = detailPop.create();
		//alert.setTitle("상세정보");
		//alert.setIcon(android.R.drawable.ic_search_category_default);
		
		alert.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		alert.show();
		*/
		
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
