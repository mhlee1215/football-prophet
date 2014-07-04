package com.JSCorp.wp.adapter;

import java.util.ArrayList;
import java.util.List;
import com.JSCorp.wp.R;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.GameService;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	
	@SuppressLint("NewApi")
	public void detailInfo(FPUser user){
		Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point size = new Point();
        display.getSize(size); 
        int width = size.x;
        int height = size.y;
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		
		final Dialog dialog = new Dialog(mContext);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    dialog.setContentView(R.layout.rank_dialog);
	    
	    lp.copyFrom(dialog.getWindow().getAttributes());
	    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
	    lp.height = height - (height / 4);
	    System.out.println("height: " + lp.height);
	    
	    dialog.getWindow().setAttributes(lp);

	    
	    
	    System.out.println(">>U>>"+user);
	    
	    ((TextView) dialog.findViewById(R.id.nickname)).setText(user.getNickname());
	    ((TextView) dialog.findViewById(R.id.tag1)).setText(user.getTag());
	    String rate_str = "예언 적중률: " + String.format("%.1f", user.getRight_prophet_ratio() * 100) + "% 예언적중/총예언수: " + String.valueOf(user.getRight_prophet_num()) + "/" + String.valueOf(user.getProphet_num());
	    ((TextView) dialog.findViewById(R.id.rate)).setText(rate_str);
	    
	    printUserPredictions(dialog, user.getId());
	    
	    
	    /*
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
	    
	    Button userPredictionListButton = (Button) dialog.findViewById(R.id.btnPredictionList);
	    // if button is clicked, close the custom dialog
    	final int pos = position;
    	final List<FPUser> tar = target;
    	userPredictionListButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	userPredictionInfo(pos, tar);
	        }
	    });
	    */
	    
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
	
	@SuppressLint("NewApi")
	public void detailInfo(int position, List<FPUser> target) {
		List<FPUser> t = userRanks;
	    if(target != null) t = target;
	    FPUser user = t.get(position);
	    
	    detailInfo(user);
	}
	
	public void printUserPredictions(Dialog dialog, int userID) {
		ArrayList<FPGameMatchSchedule> userPredictionList = GameService.getGameMatchSchedules(userID);
		
		//System.out.println(userPredictionList.get());
		String gameID = "game16_";
		for(int i = 0; i < 16; i++) {

			gameID = "game16_" + (i + 1);
			
			if(i > 7) {
				gameID = "game8_" + (i - 7);
			}
			if(i > 11) {
				gameID = "game4_" + (i - 11);
			}
			if(i == 14) {
				gameID = "game34";
			}
			if(i == 15) {
				gameID = "gameFinal";
			} 
			int position = 48 + i;
			
			int resID = mContext.getResources().getIdentifier(gameID, "id", mContext.getPackageName());
		    LinearLayout layout = (LinearLayout) dialog.findViewById(resID);
		    System.out.println("Layout: " +gameID);
			String homeImage = "flag" + Integer.toString(userPredictionList.get(position).getHome_team_id());
		    String awayImage = "flag" + Integer.toString(userPredictionList.get(position).getAway_team_id());
		    int resIDHome = mContext.getResources().getIdentifier(homeImage, "drawable", mContext.getPackageName());
		    int resIDAway = mContext.getResources().getIdentifier(awayImage, "drawable", mContext.getPackageName());
			((ImageView) layout.findViewById(R.id.nations_home)).setImageResource(resIDHome);
			((ImageView) layout.findViewById(R.id.nations_away)).setImageResource(resIDAway);
			
			((TextView) layout.findViewById(R.id.matchType)).setVisibility(View.GONE);
			
			if(userPredictionList.get(position).getMatch_finished().equals("N")) {
				(layout.findViewById(R.id.predictionResult)).setVisibility(View.GONE);
				(layout.findViewById(R.id.myPrediction)).setVisibility(View.VISIBLE);
				
				 if(userPredictionList.get(position).getProphet_home_win() == 1) {
					 ((TextView) layout.findViewById(R.id.myPrediction)).setText((userPredictionList.get(position).getHome_team_name()) + " 승리 예언");
				 } else if(userPredictionList.get(position).getProphet_draw() == 1) {
				   	((TextView) layout.findViewById(R.id.myPrediction)).setText("무승부 예언");
				 } else if(userPredictionList.get(position).getProphet_away_win() == 1) {
				   	((TextView) layout.findViewById(R.id.myPrediction)).setText((userPredictionList.get(position).getAway_team_name()) + " 승리 예언");
				 } else{
				  	((TextView) layout.findViewById(R.id.myPrediction)).setText("");
				 }
				 (layout.findViewById(R.id.matchResult)).setVisibility(View.GONE);
				 
				 
			} else if(userPredictionList.get(position).getMatch_finished().equals("Y")) {
				(layout.findViewById(R.id.myPrediction)).setVisibility(View.GONE);
				(layout.findViewById(R.id.predictionResult)).setVisibility(View.VISIBLE);
				
				(layout.findViewById(R.id.matchResult)).setVisibility(View.VISIBLE);
				
				int homeScore = userPredictionList.get(position).getHome_team_score();
				int awayScore = userPredictionList.get(position).getAway_team_score();
				System.out.println("Layout: " +homeScore);
				System.out.println("Layout: " +awayScore);
				if(homeScore > awayScore) {
					((TextView) layout.findViewById(R.id.matchResult)).setText((userPredictionList.get(position).getHome_team_name()) + " 승리");
				} else {
					((TextView) layout.findViewById(R.id.matchResult)).setText((userPredictionList.get(position).getAway_team_name()) + " 승리");
				}
				
				 if(userPredictionList.get(position).getProphet_home_win() == 1) {
					 if(homeScore > awayScore) {
						 ((ImageView) layout.findViewById(R.id.predictionResult)).setImageResource(R.drawable.hit55);
					 } else {
						 ((ImageView) layout.findViewById(R.id.predictionResult)).setImageResource(R.drawable.miss55);
					 }
				 } else if(userPredictionList.get(position).getProphet_draw() == 1) {
					 if(homeScore == awayScore) {
						 ((ImageView) layout.findViewById(R.id.predictionResult)).setImageResource(R.drawable.hit55);
					 } else {
						 ((ImageView) layout.findViewById(R.id.predictionResult)).setImageResource(R.drawable.miss55);
					 }
				 } else if(userPredictionList.get(position).getProphet_away_win() == 1) {
					 if(homeScore < awayScore) {
						 ((ImageView) layout.findViewById(R.id.predictionResult)).setImageResource(R.drawable.hit55);
					 } else {
						 ((ImageView) layout.findViewById(R.id.predictionResult)).setImageResource(R.drawable.miss55);
					 }
				 } else{
					 (layout.findViewById(R.id.myPrediction)).setVisibility(View.VISIBLE);
						(layout.findViewById(R.id.predictionResult)).setVisibility(View.GONE);
						((TextView) layout.findViewById(R.id.myPrediction)).setText(" 예언 없음 ");
				 }
			} else if(userPredictionList.get(position).getMatch_finished().equals("NOW")) {
				(layout.findViewById(R.id.predictionResult)).setVisibility(View.GONE);
				(layout.findViewById(R.id.myPrediction)).setVisibility(View.VISIBLE);	
				
				(layout.findViewById(R.id.matchResult)).setVisibility(View.GONE);
				
				 if(userPredictionList.get(position).getProphet_home_win() == 1) {
					 ((TextView) layout.findViewById(R.id.myPrediction)).setText((userPredictionList.get(position).getHome_team_name()) + " 승리 예언");
				 } else if(userPredictionList.get(position).getProphet_draw() == 1) {
				   	((TextView) layout.findViewById(R.id.myPrediction)).setText("무승부 예언");
				 } else if(userPredictionList.get(position).getProphet_away_win() == 1) {
				   	((TextView) layout.findViewById(R.id.myPrediction)).setText((userPredictionList.get(position).getAway_team_name()) + " 승리 예언");
				 } else{
				  	((TextView) layout.findViewById(R.id.myPrediction)).setText("");
				 }
			}
		}
	}
}
