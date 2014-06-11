package com.JSCorp.wp.adapter;

import com.JSCorp.wp.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RankListAdapter extends BaseAdapter {
	
	LayoutInflater inflater;
	String[] categorya;
	Context mContext;
	int mListLayout;
	public String TAG = "listAdapter";
	public int listCount = 0;
	
	public RankListAdapter(Context tContext, int listLayout, String[] tmpa) {
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
		
		((TextView) convertView.findViewById(R.id.dynamicRankView1)).setText(categorya[position]);
		
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
	    dialog.setContentView(R.layout.rank_dialog);

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
	    
	    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	    dialog.show();
	}

}
