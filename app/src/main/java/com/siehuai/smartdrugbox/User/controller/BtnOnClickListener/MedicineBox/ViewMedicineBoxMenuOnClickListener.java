package com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.MedicineBox;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;
import com.siehuai.smartdrugbox.User.view.MedicineBox.ViewMedicineBoxCompartmentFragment;

import java.util.Observable;
import java.util.Observer;

public class ViewMedicineBoxMenuOnClickListener implements Observer {

    private Context mContext;
    Fragment mFragment;
    MedicineBoxCompartmentRemoteHelper mRemoteHelper;

    public ViewMedicineBoxMenuOnClickListener(Context context, Fragment fragment) {
        mContext = context;
        mFragment = fragment;
        mRemoteHelper = new MedicineBoxCompartmentRemoteHelper();
    }

    @Override
    public void update(Observable o, Object arg) {
        MedicineBoxDetails medicineBoxDetails = (MedicineBoxDetails) arg;
        mRemoteHelper.find(medicineBoxDetails.getId());
        ViewMedicineBoxCompartmentFragment compartmentFragment = new ViewMedicineBoxCompartmentFragment();
        FragmentTransaction fragmentTransaction = mFragment.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_getDrugStore, compartmentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
