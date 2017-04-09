package com.siehuai.smartdrugbox.controller.User.Adapter;

import android.view.View;

import com.siehuai.smartdrugbox.controller.Adapter.GenericRenderAdapter;
import com.siehuai.smartdrugbox.data.MenuResource.MenuResource;

public class MedicineDetailsRenderAdapter extends GenericRenderAdapter {

    @Override
    public void setResourceArrayList(MenuResource menuResource) {
        mMenuResource = menuResource;
    }

    @Override
    public void setViewOnClickListener(View.OnClickListener listener) {
        mOnClickListener = listener;
    }
}
