package com.siehuai.smartdrugbox.controller.Adapter;

import android.view.View;

import com.siehuai.smartdrugbox.data.MenuResource.MenuResource;

/**
 * Created by Asus on 4/1/2017.
 */

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
