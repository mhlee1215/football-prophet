package com.JSCorp.wp.adapter;

import java.util.List;

import com.JSCorp.wp.R;
import com.JSCorp.wp.domain.FPUser;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
	List<FPUser> userRanks;
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
	
	public RankListAdapter(Context tContext, int listLayout, List<FPUser> userRanks) {
		mContext = tContext;
		mListLayout = listLayout;
		this.userRanks = userRanks;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(this.userRanks != null) {
			listCount = userRanks.size();
		}
		
		System.out.println("listCount: "+listCount);
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
		
		
		
		((TextView) convertView.findViewById(R.id.nickname)).setText(userRanks.get(position).getNickname());
		((TextView) convertView.findViewById(R.id.rank)).setText(Integer.toString(userRanks.get(position).getRank())+"위");
		((TextView) convertView.findViewById(R.id.tag)).setText(userRanks.get(position).getTag());
		
		convertView.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				detailInfo(positionInt);
			}
		});
		
		return convertView;
	}
	
	public void detailInfo(int position) {
		detailInfo(position, null);
	}
	
	public void detailInfo(int position, List<FPUser> target) {

		
		final Dialog dialog = new Dialog(mContext);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    dialog.setContentView(R.layout.rank_dialog);

	    // set the custom dialog components - text and button
	    //TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
	    //TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);

	    List<FPUser> t = userRanks;
	    if(target != null) t = target;
	    
	    FPUser u = t.get(position);
	    
	    System.out.println(">>U>>"+u);
	    
	    ((TextView) dialog.findViewById(R.id.nickname)).setText(u.getNickname());
	    ((TextView) dialog.findViewById(R.id.tag1)).setText(u.getTag());
	    String rate_str = "적중률: " + String.format("%.1f", u.getRight_prophet_ratio() * 100) + "%";
	    ((TextView) dialog.findViewById(R.id.rate)).setText(rate_str);
	    
	    
	    
	    if("1".equals(u.getIs_facebook_visible()) || "Y".equals(u.getIs_facebook_visible())){
	    	Button facebookButton = (Button) dialog.findViewById(R.id.btnFacebook);
		    // if button is clicked, close the custom dialog
		    facebookButton.setOnClickListener(new View.OnClickListener() {
		    	String url;
		        @Override
		        public void onClick(View v) {
		        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/"+url));
		    	    mContext.startActivity(browserIntent);
		        }
		        
		        public View.OnClickListener setUrl(String url){
		        	this.url = url;
		        	return this;
		        }
		    }.setUrl(u.getFacebook())); 
	    }else{
	    	dialog.findViewById(R.id.btnFacebook).setVisibility(View.INVISIBLE);
	    }
	    
	    
	    if("1".equals(u.getIs_twitter_visible()) || "Y".equals(u.getIs_twitter_visible())){
		    Button twitterButton = (Button) dialog.findViewById(R.id.btnTwitter);
		    // if button is clicked, close the custom dialog
		    twitterButton.setOnClickListener(new View.OnClickListener() {
		    	String url;
		        @Override
		        public void onClick(View v) {
		        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/"+url));
		    	    mContext.startActivity(browserIntent);
		        }
		        public View.OnClickListener setUrl(String url){
		        	this.url = url;
		        	return this;
		        }
		    }.setUrl(u.getTwitter())); 
	    }else{
	    	dialog.findViewById(R.id.btnTwitter).setVisibility(View.INVISIBLE);
	    }
	    
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
	/*
	public void refreshFragment() {
		notifyDataSetChanged();
	}
	*/
}
