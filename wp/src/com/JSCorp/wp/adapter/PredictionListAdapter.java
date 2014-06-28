package com.JSCorp.wp.adapter;

import java.util.List;

import com.JSCorp.wp.R;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPGameProphet;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.var.GlobalVars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
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
import android.widget.ListView;
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
	
	public PredictionListAdapter(Context tContext, int listLayout, List<FPGameMatchSchedule> tmpa) {
		mContext = tContext;
		mListLayout = listLayout;
		matches = tmpa;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(matches != null) {
			listCount = matches.size();
		}
	}
	
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
		//System.out.println("Pos:"+position+", "+matches.get(position));
		String homeImage = "flag" + Integer.toString(matches.get(position).getHome_team_id());
	    String awayImage = "flag" + Integer.toString(matches.get(position).getAway_team_id());
	    int resIDHome = mContext.getResources().getIdentifier(homeImage, "drawable", mContext.getPackageName());
	    int resIDAway = mContext.getResources().getIdentifier(awayImage, "drawable", mContext.getPackageName());
		((ImageView) convertView.findViewById(R.id.nations_home)).setImageResource(resIDHome);
		((ImageView) convertView.findViewById(R.id.nations_away)).setImageResource(resIDAway);
		
		if(matches.get(position).getMatch_finished().equals("N")) {
			((TextView) convertView.findViewById(R.id.matchResult)).setVisibility(View.GONE);
			(convertView.findViewById(R.id.predictionResult)).setVisibility(View.GONE);
			((TextView) convertView.findViewById(R.id.matchTime)).setVisibility(View.VISIBLE);
			(convertView.findViewById(R.id.myPrediction)).setVisibility(View.VISIBLE);
			
			String matchTime = "\n " + matches.get(position).getTime();
			if((matches.get(position).getMonth()).equals("06")) {
				matchTime = "June " + (matches.get(position).getDay()) + matchTime;
			} else {
				matchTime = "July " + (matches.get(position).getDay()) + matchTime;
			}
			((TextView) convertView.findViewById(R.id.matchTime)).setText(matchTime);
			
			
			 if(matches.get(position).getProphet_home_win() == 1) {
				 ((TextView) convertView.findViewById(R.id.myPrediction)).setText((matches.get(position).getHome_team_name()) + "\n승리 예언");
			 } else if(matches.get(position).getProphet_draw() == 1) {
			   	((TextView) convertView.findViewById(R.id.myPrediction)).setText("무승부 예언");
			 } else if(matches.get(position).getProphet_away_win() == 1) {
			   	((TextView) convertView.findViewById(R.id.myPrediction)).setText((matches.get(position).getAway_team_name()) + "\n승리 예언");
			 } else{
			  	((TextView) convertView.findViewById(R.id.myPrediction)).setText("");
			 }
			 
		} else if(matches.get(position).getMatch_finished().equals("Y")) {
			((TextView) convertView.findViewById(R.id.matchTime)).setVisibility(View.GONE);
			(convertView.findViewById(R.id.myPrediction)).setVisibility(View.GONE);
			((TextView) convertView.findViewById(R.id.matchResult)).setVisibility(View.VISIBLE);
			(convertView.findViewById(R.id.predictionResult)).setVisibility(View.VISIBLE);
			
			String matchResult = "";
			int homeScore = matches.get(position).getHome_team_score();
			int awayScore = matches.get(position).getAway_team_score();
			if(homeScore > awayScore) {
				matchResult = matches.get(position).getHome_team_name() + " 승리\n  " + matches.get(position).getHome_team_score() + " : " + matches.get(position).getAway_team_score();
			} else if(homeScore == awayScore) {
				matchResult = "무승부\n " + matches.get(position).getHome_team_score() + " : " + matches.get(position).getAway_team_score();
			} else {
				matchResult = matches.get(position).getAway_team_name() + " 승리\n" + matches.get(position).getHome_team_score() + " : " + matches.get(position).getAway_team_score();
			}
			
			((TextView) convertView.findViewById(R.id.matchResult)).setText(matchResult);
			
			
			 if(matches.get(position).getProphet_home_win() == 1) {
				 if(homeScore > awayScore) {
					 ((ImageView) convertView.findViewById(R.id.predictionResult)).setImageResource(R.drawable.hit55);
				 } else {
					 ((ImageView) convertView.findViewById(R.id.predictionResult)).setImageResource(R.drawable.miss55);
				 }
			 } else if(matches.get(position).getProphet_draw() == 1) {
				 if(homeScore == awayScore) {
					 ((ImageView) convertView.findViewById(R.id.predictionResult)).setImageResource(R.drawable.hit55);
				 } else {
					 ((ImageView) convertView.findViewById(R.id.predictionResult)).setImageResource(R.drawable.miss55);
				 }
			 } else if(matches.get(position).getProphet_away_win() == 1) {
				 if(homeScore < awayScore) {
					 ((ImageView) convertView.findViewById(R.id.predictionResult)).setImageResource(R.drawable.hit55);
				 } else {
					 ((ImageView) convertView.findViewById(R.id.predictionResult)).setImageResource(R.drawable.miss55);
				 }
			 } else{
				 (convertView.findViewById(R.id.myPrediction)).setVisibility(View.VISIBLE);
					(convertView.findViewById(R.id.predictionResult)).setVisibility(View.GONE);
					((TextView) convertView.findViewById(R.id.myPrediction)).setText(" - ");
			 }
		} else if(matches.get(position).getMatch_finished().equals("NOW")) {
			((TextView) convertView.findViewById(R.id.matchResult)).setVisibility(View.GONE);
			(convertView.findViewById(R.id.predictionResult)).setVisibility(View.GONE);
			((TextView) convertView.findViewById(R.id.matchTime)).setVisibility(View.VISIBLE);
			(convertView.findViewById(R.id.myPrediction)).setVisibility(View.VISIBLE);
			
			((TextView) convertView.findViewById(R.id.matchTime)).setText("경기 진행중");
			
			
			 if(matches.get(position).getProphet_home_win() == 1) {
				 ((TextView) convertView.findViewById(R.id.myPrediction)).setText((matches.get(position).getHome_team_name()) + "\n승리 예언");
			 } else if(matches.get(position).getProphet_draw() == 1) {
			   	((TextView) convertView.findViewById(R.id.myPrediction)).setText("무승부 예언");
			 } else if(matches.get(position).getProphet_away_win() == 1) {
			   	((TextView) convertView.findViewById(R.id.myPrediction)).setText((matches.get(position).getAway_team_name()) + "\n승리 예언");
			 } else{
			  	((TextView) convertView.findViewById(R.id.myPrediction)).setText("");
			 }
		}
		   
		boolean isMatchFinished = true;
		if("N".equals(matches.get(position).getMatch_finished())){
			isMatchFinished = false;
		}
		
		
		
		convertView.setOnClickListener(new Button.OnClickListener() {
			boolean isMatchFinished;
			public void onClick(View v) {
				
				System.out.println("isMatchFinished : "+isMatchFinished);
				if(isMatchFinished){
					System.out.println("깝 ㄴㄴ 끝남");
				}else{
					detailInfo(positionInt);	
				}
				
			}
			
			public Button.OnClickListener init(boolean isMatchFinished){
				this.isMatchFinished = isMatchFinished;
				return this;
			}
		}.init(isMatchFinished));
		
		return convertView;
	}
	
	public void detailInfo(int position) {
		System.out.println("매치@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		final Dialog dialog = new Dialog(mContext);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    dialog.setContentView(R.layout.prediction_dialog);
	    
	    
	    
	    System.out.println(">>>>"+matches.get(position));
	    if(matches.get(position).getProphet_home_win() == 1) {
	    	((TextView) dialog.findViewById(R.id.btnPredict1)).setText(matches.get(position).getHome_team_name() + "\n 승리 예언");
	    	((TextView) dialog.findViewById(R.id.btnPredict2)).setText("무승부");
	    	((TextView) dialog.findViewById(R.id.btnPredict3)).setText(matches.get(position).getAway_team_name() + "\n 승리");
	    	((Button) dialog.findViewById(R.id.btnPredict1)).setBackgroundResource(R.drawable.dialog_selected_button);
	    	((Button) dialog.findViewById(R.id.btnPredict2)).setBackgroundResource(R.drawable.dialog_button);
	    	((Button) dialog.findViewById(R.id.btnPredict3)).setBackgroundResource(R.drawable.dialog_button);
	    } else if(matches.get(position).getProphet_draw() == 1) {
	    	((TextView) dialog.findViewById(R.id.btnPredict1)).setText(matches.get(position).getHome_team_name() + "\n 승리");
	    	((TextView) dialog.findViewById(R.id.btnPredict2)).setText("무승부 예언");
	    	((TextView) dialog.findViewById(R.id.btnPredict3)).setText(matches.get(position).getAway_team_name() + "\n 승리");
	    	((Button) dialog.findViewById(R.id.btnPredict2)).setBackgroundResource(R.drawable.dialog_selected_button);
	    	((Button) dialog.findViewById(R.id.btnPredict1)).setBackgroundResource(R.drawable.dialog_button);
	    	((Button) dialog.findViewById(R.id.btnPredict3)).setBackgroundResource(R.drawable.dialog_button);
	    } else if(matches.get(position).getProphet_away_win() == 1) {
	    	((TextView) dialog.findViewById(R.id.btnPredict1)).setText(matches.get(position).getHome_team_name() + "\n 승리");
	    	((TextView) dialog.findViewById(R.id.btnPredict2)).setText("무승부");
	    	((TextView) dialog.findViewById(R.id.btnPredict3)).setText(matches.get(position).getAway_team_name() + "\n 승리 예언");
	    	((Button) dialog.findViewById(R.id.btnPredict3)).setBackgroundResource(R.drawable.dialog_selected_button);
	    	((Button) dialog.findViewById(R.id.btnPredict1)).setBackgroundResource(R.drawable.dialog_button);
	    	((Button) dialog.findViewById(R.id.btnPredict2)).setBackgroundResource(R.drawable.dialog_button);
	    } else {
	    	((TextView) dialog.findViewById(R.id.btnPredict1)).setText(matches.get(position).getHome_team_name() + "\n 승리");
	    	((TextView) dialog.findViewById(R.id.btnPredict2)).setText("무승부");
	    	((TextView) dialog.findViewById(R.id.btnPredict3)).setText(matches.get(position).getAway_team_name() + "\n 승리");
	    }
	    
	    ((TextView)dialog.findViewById(R.id.matchGroup)).setText(matches.get(position).getGameGroup() + "조 조별매치");
	    
	    String matchTime = (matches.get(position).getMonth()) + "." + (matches.get(position).getDay()) + "." + (matches.get(position).getTime());
		((TextView) dialog.findViewById(R.id.matchDate)).setText(matchTime);
	    
	    //System.out.println(matches);
	    String homeImage = "flag" + Integer.toString(matches.get(position).getHome_team_id());
	    String awayImage = "flag" + Integer.toString(matches.get(position).getAway_team_id());
	    int resIDHome = mContext.getResources().getIdentifier(homeImage, "drawable", mContext.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagA)).setImageResource(resIDHome );;
	    int resIDAway = mContext.getResources().getIdentifier(awayImage, "drawable", mContext.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagB)).setImageResource(resIDAway );;
	    
	    //String nationHomeName = matches.get(position).getHome_team_name();
	    //String nationAwayName = matches.get(position).getAway_team_name();
	    /*
	    if(nationHomeName.equals("보스니아 헤르체고비나")) {
	    	nationHomeName = nationHomeName.replace(" ", "\n");
	    }
	    if(nationAwayName.equals("보스니아 헤르체고비나")) {
	    	nationAwayName = nationAwayName.replace(" ", "\n");
	    } 
	    */
	    //((TextView) dialog.findViewById(R.id.nationA)).setText(nationHomeName);
	    //((TextView) dialog.findViewById(R.id.nationB)).setText(nationAwayName);
	    
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
	    Button predictionButton2 = (Button) dialog.findViewById(R.id.btnPredict2);
	    Button predictionButton3 = (Button) dialog.findViewById(R.id.btnPredict3);
	    
	    System.out.println("매치@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@: " + matches.get(position));
	    if((matches.get(position).getMatch_finished().equals("Y")) || (matches.get(position).getMatch_finished().equals("NOW"))) {
	    	System.out.println("매치@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@: " + matches.get(position));
	    	dialog.findViewById(R.id.btnPredict1).setVisibility(View.GONE);
	    	dialog.findViewById(R.id.btnPredict2).setVisibility(View.GONE);
	    	dialog.findViewById(R.id.btnPredict3).setVisibility(View.GONE);
	    } else {
	    
	    predictionButton1.setOnClickListener(new View.OnClickListener() {

	    	int position;
	    	
	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            predictHomeVictory(position);
	            notifyDataSetChanged();
	        }
	        
	        public View.OnClickListener init(int position){
	        	this.position = position;
	        	return this;
	        }

	    }.init(position));
	    
	    
	    predictionButton2.setOnClickListener(new View.OnClickListener() {

	    	int position;
	    	
	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            predictDraw(position);
	            notifyDataSetChanged();
	        }
	        
	        public View.OnClickListener init(int position){
	        	this.position = position;
	        	return this;
	        }
	    }.init(position)); 
	    
	    
	    predictionButton3.setOnClickListener(new View.OnClickListener() {

	    	int position;
	    	
	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            predictAwayVictory(position);
	            notifyDataSetChanged();
	        }
	        
	        public View.OnClickListener init(int position){
	        	this.position = position;
	        	return this;
	        }
	    }.init(position)); 
	    
	    }
	    
	    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	    dialog.show();
	}
	
	private void predictHomeVictory(int position) {
		// TODO Auto-generated method stub
		Log.i("onClick", "1");
		//matches.get(position)
		matches.get(position).setProphet_home_win(1);
		
		FPGameProphet gameProphet = new FPGameProphet();
		gameProphet.setUser_id(GlobalVars.user.getId());
		gameProphet.setMatch_id(position+1);
		gameProphet.setProphet_type("1");
		gameProphet.setHome_team_win();
		
		new SetProphetStatus().doInBackground(gameProphet);
	}
	private void predictDraw(int position) {
		// TODO Auto-generated method stub
		Log.i("onClick", "2");
		matches.get(position).setProphet_draw(1);
		
		FPGameProphet gameProphet = new FPGameProphet();
		gameProphet.setUser_id(GlobalVars.user.getId());
		gameProphet.setMatch_id(position+1);
		gameProphet.setProphet_type("1");
		gameProphet.setDraw();
		
		new SetProphetStatus().doInBackground(gameProphet);
	}
	private void predictAwayVictory(int position) {
		// TODO Auto-generated method stub
		Log.i("onClick", "3");
		matches.get(position).setProphet_away_win(1);
		
		FPGameProphet gameProphet = new FPGameProphet();
		gameProphet.setUser_id(GlobalVars.user.getId());
		gameProphet.setMatch_id(position+1);
		gameProphet.setProphet_type("1");
		gameProphet.setAway_team_win();
		
		new SetProphetStatus().doInBackground(gameProphet);
	}
	
	public class SetProphetStatus extends AsyncTask {
		@Override
		protected Object doInBackground(Object... arg0) {
			FPGameProphet gameProphet = (FPGameProphet) arg0[0];
			// TODO Auto-generated method stub
			StrictMode.enableDefaults();
			System.out.println("UPDATE!!");
			GameService.setGameProphet(gameProphet);
			//System.out.println("MATCH SIZE:"+matches.size());
			return null;
		}
	}

}
