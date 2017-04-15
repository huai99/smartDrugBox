package com.siehuai.smartdrugbox.User.view;

import android.os.Bundle;

import com.siehuai.smartdrugbox.Generic.data.TabLayoutResource.ITabLayoutResource;
import com.siehuai.smartdrugbox.User.data.TabLayoutResource.UserMainTabLayoutResource;
import com.siehuai.smartdrugbox.Generic.view.TabActivity.TabHelperActivity;

public class UserViewMedicineTabActivity extends TabHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAdapter(new UserMainTabLayoutResource());
    }

    @Override
    protected void createAdapter(ITabLayoutResource iTabLayoutResource) {
        super.createAdapter(iTabLayoutResource);
    }
}
