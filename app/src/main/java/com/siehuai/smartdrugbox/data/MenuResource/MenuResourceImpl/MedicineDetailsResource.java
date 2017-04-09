package com.siehuai.smartdrugbox.data.MenuResource.MenuResourceImpl;

import com.siehuai.smartdrugbox.data.MenuResource.MenuResource;

import java.util.ArrayList;

/**
 * Created by Asus on 4/1/2017.
 */

public class MedicineDetailsResource implements MenuResource {

    private ArrayList<Integer> mImageList;
    private ArrayList<String> mTextList;

    @Override
    public void setResourceImgList(ArrayList<?> imgList) {
        mImageList = (ArrayList<Integer>) imgList;
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
