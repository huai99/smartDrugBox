package com.siehuai.smartdrugbox.User.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MedicineDetailsMenuResource implements MenuResource {

    private ArrayList<Bitmap> mImageList = new ArrayList<>();
    private ArrayList<String> mTextList = new ArrayList<>();
    private ArrayList<MedicineDetails> mMedicineDetailsList;
    private Map<String, Bitmap> imgHashMap = new HashMap<>();


    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        mMedicineDetailsList = (ArrayList<MedicineDetails>) resourceList;
        setResourceImgList();
        setResourceTextList();
    }

    @Override
    public ArrayList<?> getResourceList() {
        return mMedicineDetailsList;
    }

    @Override
    public ArrayList<?> getResourceImgList() {
        return mImageList;
    }

    @Override
    public ArrayList<?> getResourceTextList() {
        return mTextList;
    }

    private void setResourceImgList() {
        mImageList.clear();
        for (MedicineDetails medicineDetails : mMedicineDetailsList) {
            if (!imgHashMap.containsKey(medicineDetails.getId())) {
                Bitmap bitmap = Utils.Base64toBitMap(medicineDetails.getMedicineImage());
                mImageList.add(bitmap);
                imgHashMap.put(medicineDetails.getId(), bitmap);
            } else {
                mImageList.add(imgHashMap.get(medicineDetails.getId()));
            }
        }
    }

    private void setResourceTextList() {
        mTextList.clear();
        for (MedicineDetails medicineDetails : mMedicineDetailsList) {
            mTextList.add(medicineDetails.getPharmacyDetails().getPharmacyName());
        }
    }

    @Override
    public int getResourceSize() {
        return mMedicineDetailsList.size();
    }

    @Override
    public ArrayList<?> getResourceColorList() {
        return null;
    }
}
