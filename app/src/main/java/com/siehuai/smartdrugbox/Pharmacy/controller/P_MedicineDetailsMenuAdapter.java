package com.siehuai.smartdrugbox.Pharmacy.controller;

import com.siehuai.smartdrugbox.Generic.controller.Adapter.GenericMenuAdapter;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;

public class P_MedicineDetailsMenuAdapter extends GenericMenuAdapter {

    @Override
    public void setResourceArrayList(MenuResource menuResource) {
        mMenuResource = menuResource;
    }
}
