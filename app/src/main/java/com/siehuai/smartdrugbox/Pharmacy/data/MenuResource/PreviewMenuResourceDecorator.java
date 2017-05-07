package com.siehuai.smartdrugbox.Pharmacy.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreviewMenuResourceDecorator implements MenuResource {

    MenuResource mMenuResource;
    private ArrayList<Bitmap> mImgList = new ArrayList<>();
    private ArrayList<String> mTextList = new ArrayList<>();
    private ArrayList<P_MedicineDetails> mMedicineDetailList = new ArrayList<>();
    private Map<String, Bitmap> imgHashMap = new HashMap<>();


    public PreviewMenuResourceDecorator(MenuResource menuResource) {
        mMenuResource = menuResource;
        setResourceList(menuResource.getResourceList());
    }


    @Override
    public int getResourceSize() {
        return mMedicineDetailList.size();
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        if (resourceList != null) {
            mMedicineDetailList = new ArrayList<>();
            for (P_MedicineDetails medicineDetail : (ArrayList<P_MedicineDetails>) resourceList) {
                if (medicineDetail.isShowStatus()) {
                    mMedicineDetailList.add(medicineDetail);
                }
            }
        }
        setResourceImgList();
        setResourceTextList();
    }

    @Override
    public ArrayList<?> getResourceList() {
        return mMedicineDetailList;
    }

    @Override
    public ArrayList<?> getResourceImgList() {
        return mImgList;
    }

    @Override
    public ArrayList<?> getResourceTextList() {
        return mTextList;
    }

    public void setResourceImgList() {
        mImgList = new ArrayList<>();
        for (P_MedicineDetails medicineDetails : mMedicineDetailList) {
            if (!imgHashMap.containsKey(medicineDetails.getId())) {
                Bitmap bitmap = Utils.Base64toBitMap(medicineDetails.getMedicineImage());
                mImgList.add(bitmap);
                imgHashMap.put(medicineDetails.getId(), bitmap);
            } else {
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
}
