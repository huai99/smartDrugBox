package com.siehuai.smartdrugbox.User.view.MedicineBox;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;
import com.siehuai.smartdrugbox.Pharmacy.view.P_EditCatalogue.P_EditCatalogueDetailActivity;

import java.util.Observable;
import java.util.Observer;

public class ViewMedicineBoxMenuOnClickListener implements Observer {

    private Context mContext;

    ViewMedicineBoxMenuOnClickListener(Context context) {
        mContext = context;
    }

    @Override
    public void update(Observable o, Object arg) {
        P_MedicineDetails medicineDetails = (P_MedicineDetails) arg;
        Log.d("Finally ", medicineDetails.toString());
        Intent intent = new Intent(mContext, P_EditCatalogueDetailActivity.class);
        intent.putExtra("P_MedicineDetails", medicineDetails);
        mContext.startActivity(intent);
    }
}
