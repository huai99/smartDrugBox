package com.siehuai.smartdrugbox.Pharmacy.controller.OnClickListener.P_ViewMedicineOrder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder.P_ViewMedicineOrderDetailsFragment;

import java.util.Observable;
import java.util.Observer;

public class MedicineOrderListClickListener implements Observer {

    Fragment mFragment;

    public MedicineOrderListClickListener(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d("OrderClickListener", String.valueOf(arg));
        MedicineOrder medicineOrder = (MedicineOrder) arg;
        Bundle bundl = new Bundle();
        bundl.putParcelable("order", medicineOrder);
        P_ViewMedicineOrderDetailsFragment orderDetailsFragment = new P_ViewMedicineOrderDetailsFragment();
        orderDetailsFragment.setArguments(bundl);
        FragmentTransaction fragmentTransaction = mFragment.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(mFragment.getId(), orderDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
