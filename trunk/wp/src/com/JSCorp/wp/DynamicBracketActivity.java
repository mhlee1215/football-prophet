package com.JSCorp.wp;

import java.util.Arrays;
import java.util.List;

import com.JSCorp.wp.adapter.PredictionListAdapter;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.service.GameService;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DynamicBracketActivity extends ListActivity {

	/*
	final List<String> elements = Arrays.asList("Element 1", "Element 2", "Element 3",
			"Element 4", "Element 5");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.activity_dynamic_bracket,elements);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}
	*/
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_bracket);
		
        // Enabling Up / Back navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
        
        
//		String[] lista = getResources().getStringArray(R.array.nation);
//		
//		PredictionListAdapter listAdapter = new PredictionListAdapter(this, R.layout.fragment_dynamic_bracket, lista);
//		ListView listView = (ListView) findViewById(android.R.id.list);
//		listView.setAdapter(listAdapter);
        
        new GetGameTeamMap().execute(this);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	public void doPrint(List<FPGameMatchSchedule> matches){
		System.out.println("on post!");
		PredictionListAdapter listAdapter = new PredictionListAdapter(this, R.layout.fragment_dynamic_bracket, matches);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
		System.out.println(matches);
//		String[] lista = getResources().getStringArray(R.array.nation);
////		
//		PredictionListAdapter listAdapter = new PredictionListAdapter(this, R.layout.fragment_dynamic_bracket, lista);
//		ListView listView = (ListView) findViewById(android.R.id.list);
//		listView.setAdapter(listAdapter);
	}
	
	
	public class GetGameTeamMap extends AsyncTask {

		DynamicBracketActivity tContext;
		List<FPGameMatchSchedule> matches;
		@Override
		protected Object doInBackground(Object... arg0) {
			tContext = (DynamicBracketActivity) arg0[0];
			// TODO Auto-generated method stub
			matches = GameService.getGameMatchSchedules();
			System.out.println("MATCH SIZE:"+matches.size());
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tContext.doPrint(matches);
		}
		
	}
}
