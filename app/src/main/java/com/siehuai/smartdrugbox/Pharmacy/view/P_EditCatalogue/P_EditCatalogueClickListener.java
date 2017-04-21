package com.siehuai.smartdrugbox.Pharmacy.view.P_EditCatalogue;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.Observable;
import java.util.Observer;

public class P_EditCatalogueClickListener implements Observer {

    private Context mContext;

    public P_EditCatalogueClickListener(Context context) {
        mContext = context;
    }

    @Override
    public void update(Observable o, Object arg) {
        P_MedicineDetails medicineDetails = (P_MedicineDetails) arg;
        Log.d("Finally ", medicineDetails.toString());
        Intent intent = new Intent(mContext, P_EditCatalogueDetailActivity.class);
        intent.putExtra("P_MedicineDetails", (Parcelable) medicineDetails);
        mContext.startActivity(intent);
    }
}
