package com.siehuai.smartdrugbox.Generic.data.TabLayoutResource;

import android.support.v4.app.Fragment;

public interface ITabLayoutResource {

    String[] getTabNames();

    Fragment[] getFragments();

    int getTabTotalNum();
}
