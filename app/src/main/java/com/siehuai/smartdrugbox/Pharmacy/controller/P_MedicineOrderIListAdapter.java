package com.siehuai.smartdrugbox.Pharmacy.controller;

import com.siehuai.smartdrugbox.Generic.controller.Adapter.GenericIListAdapter;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;

public class P_MedicineOrderIListAdapter extends GenericIListAdapter {

    @Override
    public void setResourceArrayList(IListResource listResource) {
        mIListResource = listResource;
    }
}
