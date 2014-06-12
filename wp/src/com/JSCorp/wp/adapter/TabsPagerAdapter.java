package com.JSCorp.wp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.JSCorp.wp.BracketsFragment;
import com.JSCorp.wp.RanksFragment;
import com.JSCorp.wp.SettingsFragment;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
	Context context;
	
    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
      
        	
            return new BracketsFragment(this.context);
        case 1:
            // Games fragment activity
            return new RanksFragment();
        //case 2:
            // Movies fragment activity
        //    return new AnalysisFragment();
        case 2:
            // Movies fragment activity
            return new SettingsFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}