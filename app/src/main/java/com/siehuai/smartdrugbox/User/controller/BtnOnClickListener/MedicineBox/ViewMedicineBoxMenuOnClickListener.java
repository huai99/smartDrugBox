package com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.MedicineBox;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;
import com.siehuai.smartdrugbox.User.view.MedicineBox.ViewMedicineBoxCompartmentFragment;

import java.util.Observable;
import java.util.Observer;

public class ViewMedicineBoxMenuOnClickListener implements Observer {

    private Context mContext;
    Fragment mFragment;

    public ViewMedicineBoxMenuOnClickListener(Context context, Fragment fragment) {
        mContext = context;
        mFragment = fragment;
    }

    @Override
    public void update(Observable o, Object arg) {
        MedicineBoxDetails medicineBoxDetails = (MedicineBoxDetails) arg;
        Bundle bundl = new Bundle();
        bundl.putString("id", medicineBoxDetails.getId());
        ViewMedicineBoxCompartmentFragment compartmentFragment = new ViewMedicineBoxCompartmentFragment();
        compartmentFragment.setArguments(bundl);
        FragmentTransaction fragmentTransaction = mFragment.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_getDrugStore, compartmentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
