package com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.MedicineBox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.view.MedicineBox.EditCompartmentDetailsFragment;

import java.util.Observable;
import java.util.Observer;

public class CompartmentFragmentOnClickListener implements Observer {

    Fragment mFragment;

    public CompartmentFragmentOnClickListener(Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void update(Observable o, Object arg) {
        Bundle bundl = new Bundle();
        CompartmentDetails details = (CompartmentDetails) arg;
        bundl.putParcelable("compartmentDetails",details);
        EditCompartmentDetailsFragment compartmentFragment = new EditCompartmentDetailsFragment();
        compartmentFragment.setArguments(bundl);
        FragmentTransaction fragmentTransaction = mFragment.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_getDrugStore, compartmentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
