package com.JSCorp.wp;

import java.util.List;

import com.JSCorp.wp.R;
import com.JSCorp.wp.BracketsFragment.GetGameTeamMap;
import com.JSCorp.wp.adapter.PredictionListAdapter;
import com.JSCorp.wp.adapter.PredictionListAdapter.SetProphetStatus;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPGameProphet;
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
import android.os.StrictMode;
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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class TournamentBracketFragment extends Fragment implements FirstPageFragmentListener {
	
	//static FirstPageFragmentListener firstPageListener;

	Context context;
	View rootView;
	
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

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		System.out.println("In Fragment 1-2");

		rootView = inflater.inflate(R.layout.activity_tournament_bracket, container,
				false);
		
		 if(GlobalVars.matches == null){
	        	Log.i(GlobalVars.WP_INFO_TAG, "Transaction to server for match retrieval.");
	        	new GetGameTeamMap().execute(this);
	    } else{
	        	Log.i(GlobalVars.WP_INFO_TAG, "Match found in the app. Skip match retrieval to server.");
	        	this.matches = GlobalVars.matches;
	        	doPrint();
	    }
		
		Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size); 
        int width = size.x;
        int qf_margin = (width - 80) / 8;
        int sf_margin = qf_margin * 3;
        int f_margin = ((width - 80) / 3) - ((width - 80) / 4);
        
        int bracket_margin_16 = qf_margin / 2;
        ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_16_1);
		b16_1.setPadding(bracket_margin_16, 0, bracket_margin_16, 0);
		ImageView b16_2 = (ImageView) rootView.findViewById(R.id.bracket_16_2);
		b16_2.setPadding(bracket_margin_16, 0, bracket_margin_16, 0);
		b16_2.setRotation(180);
		ImageView b16_3 = (ImageView) rootView.findViewById(R.id.bracket_16_3);
		b16_3.setPadding(bracket_margin_16, 0, bracket_margin_16, 0);
		ImageView b16_4 = (ImageView) rootView.findViewById(R.id.bracket_16_4);
		b16_4.setPadding(bracket_margin_16, 0, bracket_margin_16, 0);
		b16_4.setRotation(180);
		
		int bracket_margin_8 = qf_margin + (qf_margin / 2) - 20;
		ImageView b8_1 = (ImageView) rootView.findViewById(R.id.bracket_8_1);
		b8_1.setPadding(bracket_margin_8, 0, bracket_margin_8, 0);
		ImageView b8_2 = (ImageView) rootView.findViewById(R.id.bracket_8_2);
		b8_2.setPadding(bracket_margin_8, 0, bracket_margin_8, 0);
		b8_2.setRotation(180);
		
		ImageView b4_2 = (ImageView) rootView.findViewById(R.id.bracket_4_2);
		b4_2.setRotation(180);
        
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
        
        //16h round view onclicklistener
        for(int i = 0; i < 8; i++) {
        	String idString = "predictionResult_16_" + (i+1);
        	//String idString = "nations_vs_16_" + (i+1);
        	int predictionID = getResources().getIdentifier(idString, "id", context.getPackageName());
        	((ImageView) rootView.findViewById(predictionID)).setVisibility(View.GONE);
        	//((TextView) rootView.findViewById(predictionID)).setVisibility(View.GONE);
        	
        	String viewID = "round_of_16_" + (i+1);
        	int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
        	LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
        	final int position = 48 + i;
        	layout.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View v) {
    				detailInfo(position);
    			}
    		});
        }
        /*
        for(int i = 56; i < 60; i++) {
			if(matches.get(i).getHome_team_id() != 0) {
				String viewID = "quarter_final" + (i-56);
			    int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
			    LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
			    
			    final int position = i;
			    layout.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				detailInfo(position);
	    			}
	    		});
			}
		}
		for(int i = 60; i < 62; i++) {
			if(matches.get(i).getHome_team_id() != 0) {
				String viewID = "semi_final" + (i-60);
			    int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
			    LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
			    
			    final int position = i;
			    layout.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				detailInfo(position);
	    			}
	    		});
			}
		}
		for(int i = 62; i < 64; i++) {
			if(matches.get(i).getHome_team_id() != 0) {
				String viewID = "final" + (i-62);
			    int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
			    LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
			    
			    final int position = i;
			    layout.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				detailInfo(position);
	    			}
	    		});
			}
		}
        */
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
	    	((Button) dialog.findViewById(R.id.btnPredict2)).setVisibility(View.GONE);
	    } else if(matches.get(position).getProphet_away_win() == 1) {
	    	((TextView) dialog.findViewById(R.id.btnPredict1)).setText(matches.get(position).getHome_team_name() + "\n 승리");
	    	((TextView) dialog.findViewById(R.id.btnPredict2)).setText("무승부");
	    	((TextView) dialog.findViewById(R.id.btnPredict3)).setText(matches.get(position).getAway_team_name() + "\n 승리 예언");
	    	((Button) dialog.findViewById(R.id.btnPredict3)).setBackgroundResource(R.drawable.dialog_selected_button);
	    	((Button) dialog.findViewById(R.id.btnPredict1)).setBackgroundResource(R.drawable.dialog_button);
	    	((Button) dialog.findViewById(R.id.btnPredict2)).setBackgroundResource(R.drawable.dialog_button);
	    	((Button) dialog.findViewById(R.id.btnPredict2)).setVisibility(View.GONE);
	    } else {
	    	((TextView) dialog.findViewById(R.id.btnPredict1)).setText(matches.get(position).getHome_team_name() + "\n 승리");
	    	((TextView) dialog.findViewById(R.id.btnPredict2)).setText("무승부");
	    	((TextView) dialog.findViewById(R.id.btnPredict3)).setText(matches.get(position).getAway_team_name() + "\n 승리");
	    	((Button) dialog.findViewById(R.id.btnPredict2)).setVisibility(View.GONE);
	    }
	    
	    if((matches.get(position).getId() > 47) && (matches.get(position).getId() < 57)) {
	    	((TextView)dialog.findViewById(R.id.matchGroup)).setText("16강 제 " + (matches.get(position).getId() - 48) + "경기");
	    }
	    
	    String matchTime = (matches.get(position).getMonth()) + "." + (matches.get(position).getDay()) + "." + (matches.get(position).getTime());
		((TextView) dialog.findViewById(R.id.matchDate)).setText(matchTime);
	    
	    String homeImage = "flag" + Integer.toString(matches.get(position).getHome_team_id());
	    String awayImage = "flag" + Integer.toString(matches.get(position).getAway_team_id());
	    int resIDHome = context.getResources().getIdentifier(homeImage, "drawable", context.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagA)).setImageResource(resIDHome );;
	    int resIDAway = context.getResources().getIdentifier(awayImage, "drawable", context.getPackageName());
	    ((ImageView) dialog.findViewById(R.id.flagB)).setImageResource(resIDAway );;
	    

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
	            drawArrows(position);
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
	            drawArrows(position);
	        }
	        
	        public View.OnClickListener init(int position){
	        	this.position = position;
	        	return this;
	        }
	    }.init(position)); 
	    
	    ((Button) dialog.findViewById(R.id.btnPredict2)).setVisibility(View.GONE);
	    
	    TableRow tr = (TableRow) dialog.findViewById(R.id.matchGroupTableRow);
	    tr.setMinimumHeight(tr.getHeight() + 100);
	    
	    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	    dialog.show();
	}
	
	public void doPrint(){
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		for(int j = 48; j < matches.size(); j++) {
			System.out.println("MATCH SIZE:"+matches.get(j));
		}
		
		//arrow color
		for(int i = 48; i < 60; i+=2) {
			drawArrows(i);
		}
		
		drawArrows(60);
		drawArrows(61);
		
 		
		//16th round
		for(int i = 0; i < 8; i++) {
			String viewID = "round_of_16_" + (i+1);
		    int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
		    LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
		    final int position = 48 + i;
		    
	    	String homeImage = "flag" + Integer.toString(matches.get(position).getHome_team_id());
		    String awayImage = "flag" + Integer.toString(matches.get(position).getAway_team_id());
		    int resIDHome = getResources().getIdentifier(homeImage, "drawable", context.getPackageName());
		    int resIDAway = getResources().getIdentifier(awayImage, "drawable", context.getPackageName());
			((ImageView) layout.findViewById(R.id.nations_home)).setImageResource(resIDHome);
			((ImageView) layout.findViewById(R.id.nations_away)).setImageResource(resIDAway);
		}
		for(int i = 56; i < 60; i++) {
			if(matches.get(i).getHome_team_id() != 0) {
				String viewID = "quarter_final" + (i-55);
			    int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
			    LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
			    
			    String homeImage = "flag" + Integer.toString(matches.get(i).getHome_team_id());
			    String awayImage = "flag" + Integer.toString(matches.get(i).getAway_team_id());
			    int resIDHome = getResources().getIdentifier(homeImage, "drawable", context.getPackageName());
			    int resIDAway = getResources().getIdentifier(awayImage, "drawable", context.getPackageName());
				((ImageView) layout.findViewById(R.id.nations_home)).setImageResource(resIDHome);
				((ImageView) layout.findViewById(R.id.nations_away)).setImageResource(resIDAway);

			    
			    final int position = i;
			    layout.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				detailInfo(position);
	    			}
	    		});
			}
		}
		for(int i = 60; i < 62; i++) {
			if(matches.get(i).getHome_team_id() != 0) {
				String viewID = "semi_final" + (i-59);
			    int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
			    LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
			    
			    String homeImage = "flag" + Integer.toString(matches.get(i).getHome_team_id());
			    String awayImage = "flag" + Integer.toString(matches.get(i).getAway_team_id());
			    int resIDHome = getResources().getIdentifier(homeImage, "drawable", context.getPackageName());
			    int resIDAway = getResources().getIdentifier(awayImage, "drawable", context.getPackageName());
				((ImageView) layout.findViewById(R.id.nations_home)).setImageResource(resIDHome);
				((ImageView) layout.findViewById(R.id.nations_away)).setImageResource(resIDAway);
				
				final int position = i;
			    layout.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				detailInfo(position);
	    			}
	    		});
			}
		}
		for(int i = 62; i < 64; i++) {
			if(matches.get(i).getHome_team_id() != 0) {
				String viewID = "final2";
				if(i == 63) {
					viewID = "final1";
				}
			    int resID = getResources().getIdentifier(viewID, "id", context.getPackageName());
			    LinearLayout layout = (LinearLayout) rootView.findViewById(resID);
			    String homeImage = "flag" + Integer.toString(matches.get(i).getHome_team_id());
			    String awayImage = "flag" + Integer.toString(matches.get(i).getAway_team_id());
			    int resIDHome = getResources().getIdentifier(homeImage, "drawable", context.getPackageName());
			    int resIDAway = getResources().getIdentifier(awayImage, "drawable", context.getPackageName());
				((ImageView) layout.findViewById(R.id.nations_home)).setImageResource(resIDHome);
				((ImageView) layout.findViewById(R.id.nations_away)).setImageResource(resIDAway);
				
				final int position = i;
			    layout.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				detailInfo(position);
	    			}
	    		});
			}
		}
	}
	
	private void drawArrows(int position) {
		//48 -55
		//56 - 59
		//60 - 61
		//62 - 63
		if(position > 47 && position < 56) {
			if(position == 48 || position == 49) {
				int pos = 48;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_16_1);
				
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					}	
				}else{
					if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					}	
				}
				
				
			} else if(position == 50 || position == 51) {
				int pos = 50;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_16_2);
				
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					}		
				}else{
					if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					}	
				}
				
				
			} else if(position == 52 || position == 53) {
				int pos = 52;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_16_3);
				
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					}
				}else{
					if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					}
				}
				
				
			} else if(position == 54 || position == 55){
				int pos = 54;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_16_4);
				
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					}
				}else{
					if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa);
					} else if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh);
					} else if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h);
					} else if((matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0);
					} else if((matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0);
					}
				}
			}
		} else if(position > 55 && position < 60) {
			if(position == 56 || position == 57) {
				int pos = 56;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_8_1);
				
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh_wide);
					} else if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha_wide);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah_wide);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa_wide);
					} else if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0_wide);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0_wide);
					} else if((matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h_wide);
					} else if((matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a_wide);
					}
				}else{
					if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh_wide);
					} else if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha_wide);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah_wide);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa_wide);
					} else if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0_wide);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0_wide);
					} else if((matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h_wide);
					} else if((matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a_wide);
					}
				}
				
			
			} else if(position == 58 || position == 59) {
				int pos = 58;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_8_2);
				
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa_wide);
					} else if((matches.get(pos).getMatch_home_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha_wide);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah_wide);
					} else if((matches.get(pos).getMatch_away_win() == 1) && (matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh_wide);
					} else if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a_wide);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h_wide);
					} else if((matches.get(pos+1).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0_wide);
					} else if((matches.get(pos+1).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0_wide);
					}
				}else{
					if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_aa_wide);
					} else if((matches.get(pos).getProphet_home_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ha_wide);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_ah_wide);
					} else if((matches.get(pos).getProphet_away_win() == 1) && (matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_hh_wide);
					} else if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0a_wide);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_0h_wide);
					} else if((matches.get(pos+1).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a0_wide);
					} else if((matches.get(pos+1).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h0_wide);
					}
				}
				
			
			}
		} else if(position > 59 && position < 62) {
			if(position == 60) {
				int pos = 60;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_4_1);
				
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h_big);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a_big);
					}
				}else{
					if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h_big);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a_big);
					}
				}
				
				
			} else {
				int pos = 61;
				ImageView b16_1 = (ImageView) rootView.findViewById(R.id.bracket_4_2);
				 
				if("Y".equals(matches.get(pos).getMatch_finished())){
					if((matches.get(pos).getMatch_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a_big);
					} else if((matches.get(pos).getMatch_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h_big);
					}
				}else{
					if((matches.get(pos).getProphet_home_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_a_big);
					} else if((matches.get(pos).getProphet_away_win() == 1)) {
						b16_1.setImageResource(R.drawable.tour_arrow_h_big);
					}
				}
				
				
			}
		}
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
			tContext.doPrint();
		}
		
	}
	

	@Override
	public void onSwitchToNextFragment() {
		// TODO Auto-generated method stub
	}
}
