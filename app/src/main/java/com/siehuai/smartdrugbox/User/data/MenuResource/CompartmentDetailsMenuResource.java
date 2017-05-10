package com.siehuai.smartdrugbox.User.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CompartmentDetailsMenuResource<T> implements MenuResource {

    private ArrayList<Bitmap> mImgList = new ArrayList<>();
    private ArrayList<String> mTextList = new ArrayList<>();
    private ArrayList<Integer> mColorList = new ArrayList<>();
    private ArrayList<T> mResourceList = new ArrayList<>();
    private Map<String, Bitmap> imgHashMap = new HashMap<>();
    private int size;


    @Override
    public int getResourceSize() {
        return mResourceList.size();
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        if (resourceList != null) {
            mResourceList = (ArrayList<T>) resourceList;
            sortListBaseOnCompartmentNumber(mResourceList);
        }
        setImgList();
        setTextList();
        setColorList();
    }

    public void setImgList() {
        mImgList.clear();
        for (CompartmentDetails compartmentDetails : (ArrayList<CompartmentDetails>) mResourceList) {
            String img = null;
            String id = null;
            Bitmap bitmap = Utils.Base64toBitMap(img);
            mImgList.add(bitmap);
            imgHashMap.put(id, bitmap);
//            if (!imgHashMap.containsKey(id)) {
//                Bitmap bitmap = Utils.Base64toBitMap(img);
//                mImgList.add(bitmap);
//                imgHashMap.put(id, bitmap);
//            } else {
//                mImgList.add(imgHashMap.get(id));
//            }
        }
    }

    public void setTextList() {
        mTextList.clear();
        for (CompartmentDetails compartmentDetails : (ArrayList<CompartmentDetails>) mResourceList) {
            mTextList.add(compartmentDetails.getId());
        }
    }

    public void setColorList() {
        mColorList.clear();
        for (CompartmentDetails compartmentDetails : (ArrayList<CompartmentDetails>) mResourceList) {
            Integer color = (compartmentDetails.isFillUpStatus()) ? R.color.green_400 : R.color.red_400;
            mColorList.add(color);
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
        return mColorList;
    }

    private void sortListBaseOnCompartmentNumber(ArrayList<T> resourceList) {
        Collections.sort(resourceList, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                CompartmentDetails resource1 = (CompartmentDetails) o1;
                CompartmentDetails resource2 = (CompartmentDetails) o2;
                int id_1 = Integer.parseInt(resource1.getId());
                int id_2 = Integer.parseInt(resource2.getId());
                return id_1 - id_2;
            }
        });
    }
}
