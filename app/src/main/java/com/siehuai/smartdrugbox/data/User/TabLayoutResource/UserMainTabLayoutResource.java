package com.siehuai.smartdrugbox.data.User.TabLayoutResource;


import android.support.v4.app.Fragment;

import com.siehuai.smartdrugbox.data.TabLayoutResource.ITabLayoutResource;
import com.siehuai.smartdrugbox.view.User.MedicineCatalogueFragment;
import com.siehuai.smartdrugbox.view.User.UserCartFragment;

public class UserMainTabLayoutResource implements ITabLayoutResource {

    @Override
    public String[] getTabNames() {
        return new String[]{
                "Medicine Catalogue",
                "Cart"
        };
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{
                new MedicineCatalogueFragment(),
                new UserCartFragment()
        };
    }

    @Override
    public int getTabTotalNum() {
        return getTabNames().length;
    }
}
