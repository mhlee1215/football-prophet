package com.JSCorp.wp;

import java.util.List;

import com.JSCorp.wp.R;
import com.JSCorp.wp.BracketsFragment.GetGameTeamMap;
import com.JSCorp.wp.adapter.PredictionListAdapter;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.var.GlobalVars;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class TournamentBracketFragment extends Fragment implements FirstPageFragmentListener {
	
	//static FirstPageFragmentListener firstPageListener;

	Context context;
	
	////
	public List<FPGameMatchSchedule> matches;
	ProgressDialog dialog;
	PredictionListAdapter listAdapter;

	public TournamentBracketFragment() {
		// TODO Auto-generated constructor stub
	}

	//public TournamentBracketFragment(Context context, FirstPageFragmentListener listener) {
	public TournamentBracketFragment(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		//firstPageListener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		System.out.println("In Fragment 1-2");

		View rootView = inflater.inflate(R.layout.activity_tournament_bracket, container,
				false);
		
		 if(GlobalVars.matches == null){
	        	Log.i(GlobalVars.WP_INFO_TAG, "Transaction to server for match retrieval.");
	        	
	        	new GetGameTeamMap().execute(this);
	    } else{
	        	Log.i(GlobalVars.WP_INFO_TAG, "Match found in the app. Skip match retrieval to server.");
	        	this.matches = GlobalVars.matches;
	    }
		 
		
		Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int qf_margin = (width - 80) / 8;
        int sf_margin = qf_margin * 3;
        int f_margin = ((width - 80) / 3) - ((width - 80) / 4);
        
        LinearLayout qf1 = (LinearLayout) rootView.findViewById(R.id.quarter_final1);
        LayoutParams qf_layout1 = (LayoutParams) qf1.getLayoutParams();
        LinearLayout qf2 = (LinearLayout) rootView.findViewById(R.id.quarter_final2);
        LayoutParams qf_layout2 = (LayoutParams) qf2.getLayoutParams();
        LinearLayout qf3 = (LinearLayout) rootView.findViewById(R.id.quarter_final3);
        LayoutParams qf_layout3 = (LayoutParams) qf3.getLayoutParams();
        LinearLayout qf4 = (LinearLayout) rootView.findViewById(R.id.quarter_final4);
        LayoutParams qf_layout4 = (LayoutParams) qf4.getLayoutParams();
        
        qf_layout1.setMargins(qf_margin, 0, qf_margin, 0);
        qf1.setLayoutParams(qf_layout1);
        qf_layout2.setMargins(qf_margin, 0, qf_margin, 0);
        qf2.setLayoutParams(qf_layout2);
        qf_layout3.setMargins(qf_margin, 0, qf_margin, 0);
        qf3.setLayoutParams(qf_layout3);
        qf_layout4.setMargins(qf_margin, 0, qf_margin, 0);
        qf4.setLayoutParams(qf_layout4);
        
        LinearLayout sf1 = (LinearLayout) rootView.findViewById(R.id.semi_final1);
        LayoutParams sf_layout1 = (LayoutParams) sf1.getLayoutParams();
        LinearLayout sf2 = (LinearLayout) rootView.findViewById(R.id.semi_final2);
        LayoutParams sf_layout2 = (LayoutParams) sf2.getLayoutParams();
        
        sf_layout1.setMargins(sf_margin, 0, sf_margin, 0);
        sf1.setLayoutParams(sf_layout1);
        sf_layout2.setMargins(sf_margin, 0, sf_margin, 0);
        sf2.setLayoutParams(sf_layout2);
        
        LinearLayout f2 = (LinearLayout) rootView.findViewById(R.id.final2);
        LayoutParams f_layout2 = (LayoutParams) f2.getLayoutParams();
        f_layout2.setMargins(f_margin, 0, 0, 0);
        f2.setLayoutParams(f_layout2);
        
        LinearLayout ros1 = (LinearLayout) rootView.findViewById(R.id.round_of_16_1);
        ros1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(48);
			}
		});
        LinearLayout ros2 = (LinearLayout) rootView.findViewById(R.id.round_of_16_2);
        ros2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(49);
			}
		});
        LinearLayout ros3 = (LinearLayout) rootView.findViewById(R.id.round_of_16_3);
        ros3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(50);
			}
		});
        LinearLayout ros4 = (LinearLayout) rootView.findViewById(R.id.round_of_16_4);
        ros4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(51);
			}
		});
        LinearLayout ros5 = (LinearLayout) rootView.findViewById(R.id.round_of_16_5);
        ros5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(52);
			}
		});
        LinearLayout ros6 = (LinearLayout) rootView.findViewById(R.id.round_of_16_6);
        ros6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(53);
			}
		});
        LinearLayout ros7 = (LinearLayout) rootView.findViewById(R.id.round_of_16_7);
        ros7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(54);
			}
		});
        LinearLayout ros8 = (LinearLayout) rootView.findViewById(R.id.round_of_16_8);
        ros8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				detailInfo(55);
			}
		});
        
        return rootView;
	}
	
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
	    
	    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	    dialog.show();
	}

	public class GetGameTeamMap extends AsyncTask {
	
		TournamentBracketFragment tContext;
		List<FPGameMatchSchedule> matches;
		@Override
		protected Object doInBackground(Object... arg0) {
			
			tContext = (TournamentBracketFragment) arg0[0];
			// TODO Auto-generated method stub
			System.out.println("GLOBAL USER: "+GlobalVars.user);
			matches = GameService.getGameMatchSchedules(GlobalVars.user.getId());
			//System.out.println("MATCH SIZE:"+matches.size());
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			tContext.matches = matches;
		}
		
	}
	

	@Override
	public void onSwitchToNextFragment() {
		// TODO Auto-generated method stub
	}
}
