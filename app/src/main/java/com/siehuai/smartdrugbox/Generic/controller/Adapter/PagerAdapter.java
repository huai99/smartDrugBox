package com.siehuai.smartdrugbox.Generic.controller.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.siehuai.smartdrugbox.Generic.data.TabLayoutResource.ITabLayoutResource;

public class PagerAdapter extends FragmentStatePagerAdapter {

    ITabLayoutResource mITabLayoutResource;

    public PagerAdapter(FragmentManager fm, ITabLayoutResource tabLayoutResource) {
        super(fm);
        mITabLayoutResource = tabLayoutResource;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment[] fragments = mITabLayoutResource.getFragments();

        return fragments[position];

    }


    @Override
    public int getCount() {
        return mITabLayoutResource.getTabTotalNum();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String[] strings = mITabLayoutResource.getTabNames();
        return strings[position];
    }
}
