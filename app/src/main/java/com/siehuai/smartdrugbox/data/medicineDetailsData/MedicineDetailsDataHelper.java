package com.siehuai.smartdrugbox.data.medicineDetailsData;

import android.content.Context;

import java.util.List;

public class MedicineDetailsDataHelper {

    private Context mContext;
    private List<MedicineDetails> mMedicineDetailsList;


    public MedicineDetailsDataHelper(Context context) {
        mContext = context;
    }

    public List<MedicineDetails> getMedicineDetailsList() {
        return mMedicineDetailsList;
    }
}
