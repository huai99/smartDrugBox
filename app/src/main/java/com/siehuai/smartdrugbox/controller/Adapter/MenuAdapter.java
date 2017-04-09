package com.siehuai.smartdrugbox.controller.Adapter;

import android.view.View;

import com.siehuai.smartdrugbox.data.MenuResource.MenuResource;

/**
 * Created by Asus on 4/1/2017.
 */

public interface MenuAdapter {

    public void setResourceArrayList (MenuResource menuResource);

    public void setViewOnClickListener(View.OnClickListener listener);
}
