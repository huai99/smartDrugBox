package com.siehuai.smartdrugbox.Pharmacy.view;

import android.os.Bundle;

import com.siehuai.smartdrugbox.Generic.data.TabLayoutResource.ITabLayoutResource;
import com.siehuai.smartdrugbox.Pharmacy.data.PharmacyMainTabLayoutResource;
import com.siehuai.smartdrugbox.Generic.view.TabActivity.TabHelperActivity;

public class P_EditTabActivity extends TabHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAdapter(new PharmacyMainTabLayoutResource());
    }

    @Override
    protected void createAdapter(ITabLayoutResource iTabLayoutResource) {
        super.createAdapter(iTabLayoutResource);
    }
}
