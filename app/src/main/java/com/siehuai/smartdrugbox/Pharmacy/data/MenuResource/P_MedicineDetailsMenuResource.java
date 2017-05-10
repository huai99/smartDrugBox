package com.siehuai.smartdrugbox.Pharmacy.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class P_MedicineDetailsMenuResource implements MenuResource {

    private ArrayList<Bitmap> mImgList = new ArrayList<>();
    private ArrayList<String> mTextList = new ArrayList<>();
    private ArrayList<P_MedicineDetails> mMedicineDetailList;
    private Map<String,Bitmap> imgHashMap = new HashMap<>();

    public ArrayList<Bitmap> getResourceImgList() {
        return mImgList;
    }


    public ArrayList<String> getResourceTextList() {
        return mTextList;
    }

    public void setResourceImgList() {
        mImgList = new ArrayList<>();
        for (P_MedicineDetails medicineDetails : mMedicineDetailList) {
            if(!imgHashMap.containsKey(medicineDetails.getId())){
                Bitmap bitmap = Utils.Base64toBitMap(medicineDetails.getMedicineImage());
                mImgList.add(bitmap);
                imgHashMap.put(medicineDetails.getId(),bitmap);
            }else{
                mImgList.add(imgHashMap.get(medicineDetails.getId()));
            }
        }
    }

    public void setResourceTextList() {
        mTextList = new ArrayList<>();
        for (P_MedicineDetails medicineDetails : mMedicineDetailList) {
            mTextList.add(medicineDetails.getMedicineName());
        }
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        mMedicineDetailList = (ArrayList<P_MedicineDetails>) resourceList;
        setResourceTextList();
        setResourceImgList();
    }

    @Override
    public ArrayList<?> getResourceList() {
        return mMedicineDetailList;
    }

    @Override
    public int getResourceSize() {
        return mMedicineDetailList.size();
    }

    @Override
    public ArrayList<?> getResourceColorList() {
        return null;
    }
}
