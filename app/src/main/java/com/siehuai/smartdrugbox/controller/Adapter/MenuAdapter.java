package com.siehuai.smartdrugbox.controller.Adapter;

import android.view.View;

import com.siehuai.smartdrugbox.data.MenuResource.MenuResource;

public interface MenuAdapter {

    void setResourceArrayList(MenuResource menuResource);

    void setViewOnClickListener(View.OnClickListener listener);
}
