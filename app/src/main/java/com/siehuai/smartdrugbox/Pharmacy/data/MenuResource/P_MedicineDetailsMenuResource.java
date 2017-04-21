package com.siehuai.smartdrugbox.Pharmacy.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.ArrayList;

public class P_MedicineDetailsMenuResource implements MenuResource {

    private ArrayList<Bitmap> mImgList;
    private ArrayList<String> mTextList;
    private ArrayList<P_MedicineDetails> mMedicineDetailList;

    public ArrayList<Bitmap> getResourceImgList() {
        mImgList = new ArrayList<>();
        for (P_MedicineDetails medicineDetails : mMedicineDetailList) {
            Bitmap bitmap = Utils.Base64toBitMap(medicineDetails.getMedicineImage());
            mImgList.add(bitmap);
        }
        return mImgList;
    }


    public ArrayList<String> getResourceTextList() {
        mTextList = new ArrayList<>();
        for (P_MedicineDetails medicineDetails : mMedicineDetailList) {
            mTextList.add(medicineDetails.getMedicineName());
        }
        return mTextList;
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        mMedicineDetailList = (ArrayList<P_MedicineDetails>) resourceList;
    }

    @Override
    public ArrayList<?> getResourceList() {
        return mMedicineDetailList;
    }

    @Override
    public int getResourceSize() {
        return mMedicineDetailList.size();
    }
}
