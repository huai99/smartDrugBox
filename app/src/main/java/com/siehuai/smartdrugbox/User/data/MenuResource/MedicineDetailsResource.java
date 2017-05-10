package com.siehuai.smartdrugbox.User.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;

import java.util.ArrayList;

public class MedicineDetailsResource implements MenuResource {

    private ArrayList<Bitmap> mImageList;
    private ArrayList<String> mTextList;

    @Override
    public void setResourceList(ArrayList<?> resourceList) {

    }

    @Override
    public ArrayList<?> getResourceList() {
        return null;
    }

    @Override
    public ArrayList<?> getResourceImgList() {
        return mImageList;
    }

    @Override
    public ArrayList<?> getResourceTextList() {
        return mTextList;
    }

    @Override
    public int getResourceSize() {
        return mImageList.size();
    }

    @Override
    public ArrayList<?> getResourceColorList() {
        return null;
    }
}
