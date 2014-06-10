package com.JSCorp.wp;

import java.util.Arrays;
import java.util.List;

import com.JSCorp.wp.adapter.PredictionListAdapter;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
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
		
		// get action bar   
        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		
		String[] lista = getResources().getStringArray(R.array.nation);
		
		PredictionListAdapter listAdapter = new PredictionListAdapter(this, R.layout.fragment_dynamic_bracket, lista);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);
	}
}
