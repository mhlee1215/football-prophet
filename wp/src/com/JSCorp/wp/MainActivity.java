package com.JSCorp.wp;

import java.util.List;

import com.JSCorp.wp.adapter.TabsPagerAdapter;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.util.Log;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "나의 예언", "예언자 랭킹", /*"경기 분석",*/ "설정" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), this);

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		new GetUserInfo().execute(this);
		
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	public void doPrintUser(FPUser user){
		System.out.println(user);
	}
	
	public class GetUserInfo extends AsyncTask {

		MainActivity tContext;
		FPUser user;
		@Override
		protected Object doInBackground(Object... arg0) {
			tContext = (MainActivity) arg0[0];
			// TODO Auto-generated method stub
			
			TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			String deviceId = tManager.getDeviceId();
			System.out.println("device Id :"+deviceId);
			
			user = UserService.getUserByDeviceId(deviceId);
			System.out.println(user);
			
			if(user == null){
				//add user
				Log.i(GlobalVars.WP_INFO_TAG, "ADD USER");
				FPUser addUser = new FPUser();
				System.out.println(addUser);
				addUser.setDevice_id(deviceId);
				boolean result;
				try {
					result = UserService.addUser(addUser);
					System.out.println("result : "+result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				user = UserService.getUserByDeviceId(deviceId);
				GlobalVars.user = user;
			}else{
				Log.i(GlobalVars.WP_INFO_TAG, "USER ALREADY EXISTS");
				GlobalVars.user = user;
				System.out.println(user);
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//tContext.doPrint(matches);
		}
		
	}
}
