package com.JSCorp.wp;

import com.JSCorp.wp.adapter.RankListAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;


public class DynamicRankActivity extends ListActivity {	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_rank);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
		String[] lista = getResources().getStringArray(R.array.nation);
		
		RankListAdapter listAdapter = new RankListAdapter(this, R.layout.fragment_dynamic_rank, lista);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
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
