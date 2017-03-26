package com.siehuai.smartdrugbox.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.Adapter.PagerAdapter;

public class UserViewMedicineTabActivity extends AppCompatActivity {

    ViewPager mViewPager;
    TabLayout tabLayout;
    public static String CLASSNAME = UserViewMedicineTabActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO:Break this code up, too big chunk
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_medicine_tab);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager, CLASSNAME);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);

    }
}
