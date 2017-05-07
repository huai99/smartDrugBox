package com.siehuai.smartdrugbox.User.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MedicineBoxCompartmentMenuResource<T> implements MenuResource {

    private ArrayList<Bitmap> mImgList = new ArrayList<>();
    private ArrayList<String> mTextList = new ArrayList<>();
    private ArrayList<T> mResourceList = new ArrayList<>();
    private Map<String, Bitmap> imgHashMap = new HashMap<>();
    private int size;


    @Override
    public int getResourceSize() {
        MedicineBoxDetails boxDetails = (MedicineBoxDetails) mResourceList.get(0);
        if (boxDetails == null) {
            return 0;
        } else {
            size = boxDetails.getTotalSlotNumber();
            return size;
        }
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        if (resourceList != null) {
            mResourceList = (ArrayList<T>) resourceList;
        }
        setImgList();
        setTextList();
    }

    public void setImgList() {
        mImgList.clear();
        for (MedicineBoxCompartmentDetails compartmentDetails : (ArrayList<MedicineBoxCompartmentDetails>) mResourceList) {
            if (!imgHashMap.containsKey(compartmentDetails.getId())) {
                Bitmap bitmap = Utils.Base64toBitMap(null);
                mImgList.add(bitmap);
                imgHashMap.put(compartmentDetails.getId(), bitmap);
            } else {
                mImgList.add(imgHashMap.get(compartmentDetails.getId()));
            }
        }
        while (mImgList.size() != size) {
            mImgList.add(null);
            imgHashMap.put(null, null);
        }
    }

    public void setTextList() {
        mTextList.clear();
        for (MedicineBoxCompartmentDetails compartmentDetails : (ArrayList<MedicineBoxCompartmentDetails>) mResourceList) {
            mTextList.add(compartmentDetails.getId());
        }
        while (mTextList.size() != size) {
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
}
