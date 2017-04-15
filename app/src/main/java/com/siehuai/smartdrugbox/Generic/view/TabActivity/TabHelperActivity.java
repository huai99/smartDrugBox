package com.siehuai.smartdrugbox.Generic.view.TabActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.Generic.controller.Adapter.PagerAdapter;
import com.siehuai.smartdrugbox.Generic.data.TabLayoutResource.ITabLayoutResource;

public abstract class TabHelperActivity extends AppCompatActivity {

    ViewPager mViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_medicine_tab);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    protected void createAdapter(ITabLayoutResource iTabLayoutResource) {
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager, iTabLayoutResource);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

}
