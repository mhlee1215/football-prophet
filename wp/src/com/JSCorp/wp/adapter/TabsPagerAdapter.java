package com.JSCorp.wp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.JSCorp.wp.BracketsFragment;
import com.JSCorp.wp.FirstPageFragmentListener;
import com.JSCorp.wp.MainActivity;
import com.JSCorp.wp.TournamentBracketFragment;
import com.JSCorp.wp.RanksFragment;
import com.JSCorp.wp.SettingsFragment;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	private final class FirstPageListener /*implements FirstPageFragmentListener*/ {
		/*
        public void onSwitchToNextFragment() {
        	System.out.println("hfqowihvowhvqvnqlvlowhgevoq;hfeio;qwhoef;ch");
          mFragmentManager.beginTransaction().hide(bracketsFragment).commit();
          if(tournamentBracketFragment == null) {
        	  System.out.println("Init 1-2");
        	  tournamentBracketFragment = new TournamentBracketFragment(context, listener);
          }
          bracketsFragment = tournamentBracketFragment;
          mFragmentManager.beginTransaction().show(bracketsFragment).commit();
          //bracketsFragment = tournamentBracketFragment;
          //mFragmentManager.beginTransaction().add(tournamentBracketFragment, "TournamentFragment").commit();
          
          //mFragmentManager.beginTransaction().replace(bracketsFragment, tournamentBracketFragment).commit();            	
          notifyDataSetChanged();
        }
        */
    }
	
	private final FragmentManager mFragmentManager;
	FirstPageListener listener = new FirstPageListener();
 
	Context context;
	
	Fragment bracketsFragment;
	TournamentBracketFragment tournamentBracketFragment;
	RanksFragment ranksFragment;
	SettingsFragment settingsFragment;
	
    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mFragmentManager = fm;
        this.context = context;
        
        //bracketsFragment = new BracketsFragment(this.context, listener);
        tournamentBracketFragment =  new TournamentBracketFragment();
        ranksFragment = new RanksFragment();
        settingsFragment = new SettingsFragment();
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
        	System.out.println("fragment 1");
        	if (bracketsFragment == null) {
        		System.out.println("make new bracketsFragment 1");
        		tournamentBracketFragment = new TournamentBracketFragment(context);
        		//tournamentBracketFragment = new TournamentBracketFragment(context, listener);
        		//bracketsFragment = new BracketsFragment(this.context, listener);
        	}
            //return bracketsFragment;//new BracketsFragment(this.context);
        	return tournamentBracketFragment;
        case 1:
            // Games fragment activity
        	System.out.println("fragment 2");
        	ranksFragment.init();
            return ranksFragment;//new RanksFragment();
        //case 2:
            // Movies fragment activity
        //    return new AnalysisFragment();
        case 2:
            // Movies fragment activity
        	System.out.println("fragment 3");
            return settingsFragment;//new SettingsFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
    
    /*
    public Fragment getFragment(ViewPager container, int position, FragmentManager fm) {
        String name = makeFragmentName(container.getId(), position);
        return fm.findFragmentByTag(name);
    }

    private String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }
    */
 
}