package com.siehuai.smartdrugbox.view.User;

import android.os.Bundle;

import com.siehuai.smartdrugbox.data.TabLayoutResource.ITabLayoutResource;
import com.siehuai.smartdrugbox.data.User.TabLayoutResource.UserMainTabLayoutResource;
import com.siehuai.smartdrugbox.view.TabActivity.TabHelperActivity;

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
