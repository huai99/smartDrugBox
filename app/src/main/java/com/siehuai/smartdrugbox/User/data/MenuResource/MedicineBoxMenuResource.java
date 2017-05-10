package com.siehuai.smartdrugbox.User.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MedicineBoxMenuResource<T> implements MenuResource {

    private ArrayList<Bitmap> mImgList = new ArrayList<>();
    private ArrayList<String> mTextList = new ArrayList<>();
    private ArrayList<T> mResourceList = new ArrayList<>();
    private Map<String, Bitmap> imgHashMap = new HashMap<>();

    @Override
    public int getResourceSize() {
        return mResourceList.size();
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        mResourceList = (ArrayList<T>) resourceList;
        setImgList();
        setTextList();
    }


    public void setImgList() {
        mImgList.clear();
        for (MedicineBoxDetails medicineBoxDetails : (ArrayList<MedicineBoxDetails>) mResourceList) {
            if (!imgHashMap.containsKey(medicineBoxDetails.getId())) {
                Bitmap bitmap = Utils.Base64toBitMap(medicineBoxDetails.getUserImg());
                mImgList.add(bitmap);
                imgHashMap.put(medicineBoxDetails.getId(), bitmap);
            } else {
                mImgList.add(imgHashMap.get(medicineBoxDetails.getId()));
            }
        }
    }

    public void setTextList() {
        mTextList.clear();
        for (MedicineBoxDetails medicineBoxDetails : (ArrayList<MedicineBoxDetails>) mResourceList) {
            mTextList.add(medicineBoxDetails.getUserName());
        }
    }

    @Override
    public ArrayList<?> getResourceList() {
        return mResourceList;
    }

    @Override
    public ArrayList<?> getResourceImgList() {
        return mImgList;
    }

    @Override
    public ArrayList<?> getResourceTextList() {
        return mTextList;
    }

    @Override
    public ArrayList<?> getResourceColorList() {
        return null;
    }
}
