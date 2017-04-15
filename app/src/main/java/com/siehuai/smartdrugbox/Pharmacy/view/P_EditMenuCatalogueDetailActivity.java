package com.siehuai.smartdrugbox.Pharmacy.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.ActivityPEditMenuCatalogueDetailBinding;

public class P_EditMenuCatalogueDetailActivity extends AppCompatActivity {

    ActivityPEditMenuCatalogueDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(P_EditMenuCatalogueDetailActivity.this,
                R.layout.activity_p_edit_menu_catalogue_detail);
    }
}
