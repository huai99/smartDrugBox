package com.siehuai.smartdrugbox.Pharmacy.data;

import android.support.v4.app.Fragment;

import com.siehuai.smartdrugbox.Generic.data.TabLayoutResource.ITabLayoutResource;
import com.siehuai.smartdrugbox.Pharmacy.view.P_EditCatalogue.P_EditCatalogueFragment;
import com.siehuai.smartdrugbox.Pharmacy.view.P_PreviewCatalogueFragment;

public class PharmacyMainTabLayoutResource implements ITabLayoutResource {

    @Override
    public String[] getTabNames() {
        return new String[]{
                "Edit Catalogue",
                "Catalogue Preview"
        };
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{
                new P_EditCatalogueFragment(),
                new P_PreviewCatalogueFragment()
        };
    }

    @Override
    public int getTabTotalNum() {
        return getTabNames().length;
    }
}
