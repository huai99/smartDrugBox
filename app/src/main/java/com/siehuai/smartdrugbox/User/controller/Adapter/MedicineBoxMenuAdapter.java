package com.siehuai.smartdrugbox.User.controller.Adapter;

import com.siehuai.smartdrugbox.Generic.controller.Adapter.GenericMenuAdapter;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;

public class MedicineBoxMenuAdapter extends GenericMenuAdapter {
    @Override
    public void setResourceArrayList(MenuResource menuResource) {
        mMenuResource = menuResource;
    }
}
