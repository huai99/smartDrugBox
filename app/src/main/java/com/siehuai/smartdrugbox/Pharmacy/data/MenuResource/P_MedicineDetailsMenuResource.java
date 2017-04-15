package com.siehuai.smartdrugbox.Pharmacy.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;

import java.util.ArrayList;

public class P_MedicineDetailsMenuResource implements MenuResource {

    private ArrayList<Bitmap> mImgList;
    private ArrayList<String> mTextList;

    @Override
    public void setResourceImgList(ArrayList<?> imgList) {
        mImgList = (ArrayList<Bitmap>) imgList;
    }

    @Override
    public void setResourceTextList(ArrayList<?> textList) {
        mTextList = (ArrayList<String>) textList;

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
    public int getResourceSize() {
        return mImgList.size();
    }
}
