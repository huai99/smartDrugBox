package com.siehuai.smartdrugbox.data.MenuResource;

import java.util.ArrayList;

/**
 * Created by Asus on 4/1/2017.
 */

public interface MenuResource {

    public void setResourceImgList(ArrayList<?> imgList);

    public void setResourceTextList(ArrayList<?>textList);

    public ArrayList<?> getResourceImgList();

    public ArrayList<?> getResourceTextList();

    public int getResourceSize();
}
