package com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.OrderMedicine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.User.view.OrderMedicine.ViewRequestedMedicineDetailsFragment;

import java.util.Observable;
import java.util.Observer;

public class ViewRequestedMedicineMenuOnClickListener implements Observer{

    Fragment mFragment;

    public ViewRequestedMedicineMenuOnClickListener(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void update(Observable o, Object arg) {
        MedicineDetails medicineDetails = (MedicineDetails) arg;
        Bundle bundl = new Bundle();
        bundl.putParcelable("medicineDetails",medicineDetails);
        ViewRequestedMedicineDetailsFragment viewRequestedMedicineDetailsFragment = new ViewRequestedMedicineDetailsFragment();
        viewRequestedMedicineDetailsFragment.setArguments(bundl);
        FragmentTransaction fragmentTransaction = mFragment.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_view_requested_medicine_menu, viewRequestedMedicineDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
