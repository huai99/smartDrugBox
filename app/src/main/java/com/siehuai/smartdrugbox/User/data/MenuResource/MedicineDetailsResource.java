package com.siehuai.smartdrugbox.User.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;

import java.util.ArrayList;

public class MedicineDetailsResource implements MenuResource {

    private ArrayList<Bitmap> mImageList;
    private ArrayList<String> mTextList;

    @Override
    public void setResourceImgList(ArrayList<?> imgList) {
        mImageList = (ArrayList<Bitmap>) imgList;
    }

    @Override
    public void setResourceTextList(ArrayList<?> textList) {
        mTextList = (ArrayList<String>)textList;
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
}
