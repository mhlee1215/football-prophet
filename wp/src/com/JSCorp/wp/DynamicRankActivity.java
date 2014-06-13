package com.JSCorp.wp;

import com.JSCorp.wp.adapter.RankListAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;


public class DynamicRankActivity extends ListActivity {	
	
	private RankListAdapter listAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_rank);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
		String[] lista = getResources().getStringArray(R.array.nation);
		
		listAdapter = new RankListAdapter(this, R.layout.fragment_dynamic_rank, lista);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
		
		
		TableLayout firstPlace = (TableLayout)findViewById(R.id.firstPlace);
		TableLayout secondPlace = (TableLayout)findViewById(R.id.secondPlace);
		TableLayout thirdPlace = (TableLayout)findViewById(R.id.thirdPlace);
		firstPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(1);
			}
		});
		secondPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(1);
			}
		});
		thirdPlace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listAdapter.detailInfo(1);
			}
		});
		
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
        	//FragmentManager fm = getActivity().getSupportFragmentManager();
            //fm.popBackStack();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}
}
