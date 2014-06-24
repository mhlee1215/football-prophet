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
}