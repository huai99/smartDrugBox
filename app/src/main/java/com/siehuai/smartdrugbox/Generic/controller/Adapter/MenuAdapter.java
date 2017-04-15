package com.siehuai.smartdrugbox.Generic.controller.Adapter;

import android.view.View;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;

public interface MenuAdapter {

    void setResourceArrayList(MenuResource menuResource);

    void setViewOnClickListener(View.OnClickListener listener);
}
