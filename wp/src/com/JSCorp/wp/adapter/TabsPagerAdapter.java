package com.JSCorp.wp.adapter;

import com.JSCorp.wp.AnalysisFragment;
import com.JSCorp.wp.BracketsFragment;
import com.JSCorp.wp.RanksFragment;
import com.JSCorp.wp.SettingsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new BracketsFragment();
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