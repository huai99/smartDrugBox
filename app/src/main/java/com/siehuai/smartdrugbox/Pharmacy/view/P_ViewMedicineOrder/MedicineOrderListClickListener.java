package com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class MedicineOrderListClickListener implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Log.d("OrderClickListener", String.valueOf(arg));
    }
}
