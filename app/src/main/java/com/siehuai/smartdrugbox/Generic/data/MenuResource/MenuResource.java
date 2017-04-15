package com.siehuai.smartdrugbox.Generic.data.MenuResource;

import java.util.ArrayList;

public interface MenuResource {

    void setResourceImgList(ArrayList<?> imgList);

    void setResourceTextList(ArrayList<?> textList);

    ArrayList<?> getResourceImgList();

    ArrayList<?> getResourceTextList();

    int getResourceSize();
}
