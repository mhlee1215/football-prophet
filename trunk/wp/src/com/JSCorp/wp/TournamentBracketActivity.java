package com.JSCorp.wp;

import java.io.UnsupportedEncodingException;

import com.JSCorp.wp.EditNicknameActivity.SetUserInfo;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TournamentBracketActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tournament_bracket);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
      //test for tournament brackets
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int qf_margin = (width - 80) / 8;
        int sf_margin = qf_margin * 3;
        int f_margin = ((width - 80) / 3) - ((width - 80) / 4);
        
        LinearLayout qf1 = (LinearLayout) findViewById(R.id.quarter_final1);
        LayoutParams qf_layout1 = (LayoutParams) qf1.getLayoutParams();
        LinearLayout qf2 = (LinearLayout) findViewById(R.id.quarter_final2);
        LayoutParams qf_layout2 = (LayoutParams) qf2.getLayoutParams();
        LinearLayout qf3 = (LinearLayout) findViewById(R.id.quarter_final3);
        LayoutParams qf_layout3 = (LayoutParams) qf3.getLayoutParams();
        LinearLayout qf4 = (LinearLayout) findViewById(R.id.quarter_final4);
        LayoutParams qf_layout4 = (LayoutParams) qf4.getLayoutParams();
        
        qf_layout1.setMargins(qf_margin, 0, qf_margin, 0);
        qf1.setLayoutParams(qf_layout1);
        qf_layout2.setMargins(qf_margin, 0, qf_margin, 0);
        qf2.setLayoutParams(qf_layout2);
        qf_layout3.setMargins(qf_margin, 0, qf_margin, 0);
        qf3.setLayoutParams(qf_layout3);
        qf_layout4.setMargins(qf_margin, 0, qf_margin, 0);
        qf4.setLayoutParams(qf_layout4);
        
        LinearLayout sf1 = (LinearLayout) findViewById(R.id.semi_final1);
        LayoutParams sf_layout1 = (LayoutParams) sf1.getLayoutParams();
        LinearLayout sf2 = (LinearLayout) findViewById(R.id.semi_final2);
        LayoutParams sf_layout2 = (LayoutParams) sf2.getLayoutParams();
        
        sf_layout1.setMargins(sf_margin, 0, sf_margin, 0);
        sf1.setLayoutParams(sf_layout1);
        sf_layout2.setMargins(sf_margin, 0, sf_margin, 0);
        sf2.setLayoutParams(sf_layout2);
        
        LinearLayout f2 = (LinearLayout) findViewById(R.id.final2);
        LayoutParams f_layout2 = (LayoutParams) f2.getLayoutParams();
        f_layout2.setMargins(f_margin, 0, 0, 0);
        f2.setLayoutParams(f_layout2);
        
        LinearLayout ros1 = (LinearLayout) findViewById(R.id.round_of_16_1);
        ros1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("hahahahhahahahaha");
			}
		});
        LinearLayout ros3 = (LinearLayout) findViewById(R.id.round_of_16_3);
        ros3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("hahahahhahahahaha");
			}
		});
		
	}
	
	/*
	public void detailInfo(int position) {
		
		final Dialog dialog = new Dialog(context);
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
	    int resIDHome = context.getResources().getIdentifier(homeImage, "drawable", context.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagA)).setImageResource(resIDHome );;
	    int resIDAway = context.getResources().getIdentifier(awayImage, "drawable", context.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagB)).setImageResource(resIDAway );;
	    
	    //String nationHomeName = matches.get(position).getHome_team_name();
	    //String nationAwayName = matches.get(position).getAway_team_name();

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
	    
	    predictionButton1.setOnClickListener(new View.OnClickListener() {

	    	int position;
	    	
	        @Override
	        public void onClick(View v) {
	            dialog.dismiss();
	            predictHomeVictory(position);
	            //notifyDataSetChanged();
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
	            //notifyDataSetChanged();
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
	            //notifyDataSetChanged();
	        }
	        
	        public View.OnClickListener init(int position){
	        	this.position = position;
	        	return this;
	        }
	    }.init(position)); 
	    
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
	 */
}