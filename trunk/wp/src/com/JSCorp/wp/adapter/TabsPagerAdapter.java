package com.JSCorp.wp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.JSCorp.wp.BracketsFragment;
import com.JSCorp.wp.RanksFragment;
import com.JSCorp.wp.SettingsFragment;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
	Context context;
	
	static BracketsFragment bracketsFragment;
	static RanksFragment ranksFragment;
	static SettingsFragment settingsFragment;
	
    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        
        bracketsFragment = new BracketsFragment(this.context);
        ranksFragment = new RanksFragment();
        settingsFragment = new SettingsFragment();
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
      
        	
            return bracketsFragment;//new BracketsFragment(this.context);
        case 1:
            // Games fragment activity
        	ranksFragment.init();
            return ranksFragment;//new RanksFragment();
        //case 2:
            // Movies fragment activity
        //    return new AnalysisFragment();
        case 2:
            // Movies fragment activity
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