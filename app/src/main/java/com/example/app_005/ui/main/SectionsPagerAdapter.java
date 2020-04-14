package com.example.app_005.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.app_005.BlankFragment_001;
import com.example.app_005.BlankFragment_002;
import com.example.app_005.BlankFragment_003;
import com.example.app_005.MainActivity;
import com.example.app_005.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    MainActivity mainActivity;
    public SectionsPagerAdapter(MainActivity mainActivityAA,Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mainActivity=mainActivityAA;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //Log.i(this.toString(), "getItem: "+position);
        //return new BlankFragment_001();
        switch(position){
            case 0:
                return new BlankFragment_001().PuC_setMain(mainActivity);
            case 1:
                return new BlankFragment_002();
            case 2:
                return new BlankFragment_003();
            default:
                return PlaceholderFragment.newInstance(position + 1);
        }
       // return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}