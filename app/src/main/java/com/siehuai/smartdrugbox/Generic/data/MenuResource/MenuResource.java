package com.siehuai.smartdrugbox.Generic.data.MenuResource;

import java.util.ArrayList;

public interface MenuResource {

    int getResourceSize();

    void setResourceList(ArrayList<?> resourceList);

    ArrayList<?> getResourceList();

    ArrayList<?> getResourceImgList();

    ArrayList<?> getResourceTextList();

    ArrayList<?> getResourceColorList();

}
