package com.lookie.dongjunekim.fbl_login_fb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dongjunekim on 2017-12-12.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private SearchFragment searchFragment;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return searchFragment = SearchFragment.newInstance(position + 1);
            case 1:
                return InfoFragment.newInstance(position + 2);
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "리스트뷰";
            case 1:
                return "달력";
        }
        return null;
    }

}
