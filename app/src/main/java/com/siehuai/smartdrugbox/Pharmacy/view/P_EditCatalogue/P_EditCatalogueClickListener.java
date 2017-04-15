package com.siehuai.smartdrugbox.Pharmacy.view.P_EditCatalogue;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class P_EditCatalogueClickListener implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Log.d("Finally ",arg.toString());
    }
}
