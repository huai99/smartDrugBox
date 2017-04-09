package com.siehuai.smartdrugbox.view.Pharmacy;

import android.os.Bundle;

import com.siehuai.smartdrugbox.data.TabLayoutResource.ITabLayoutResource;
import com.siehuai.smartdrugbox.data.Pharmacy.PharmacyMainTabLayoutResource;
import com.siehuai.smartdrugbox.view.TabActivity.TabHelperActivity;

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
