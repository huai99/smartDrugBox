package com.siehuai.smartdrugbox.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.siehuai.smartdrugbox.view.MedicineCatalogueFragment;
import com.siehuai.smartdrugbox.view.UserCartFragment;
import com.siehuai.smartdrugbox.view.UserViewMedicineTabActivity;

public class PagerAdapter extends FragmentStatePagerAdapter {

    String classname;

    public PagerAdapter(FragmentManager fm, String classname) {
        super(fm);
        this.classname = classname;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        if (classname.equals(UserViewMedicineTabActivity.CLASSNAME)) {
            switch (position) {
                case 0:
                    fragment = new MedicineCatalogueFragment();
                    break;
                case 1:
                    fragment = new UserCartFragment();
                    break;
            }

            return fragment;

        } else {

            return fragment;

        }

    }


    @Override
    public int getCount() {
        if (classname.equals(UserViewMedicineTabActivity.CLASSNAME)) {
            return 2;
        } else {
            return 0;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (classname.equals(UserViewMedicineTabActivity.CLASSNAME)) {
            switch (position) {
                case 0:
                    title = "Medicine Catalogue";
                    Log.d("PagerAdapter", title);
                    break;
                case 1:
                    title = "Cart";
                    break;
            }
        } else {

        }

        return title;
    }
}
