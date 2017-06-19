package com.siehuai.smartdrugbox.Generic.data.MenuResource;

import java.util.ArrayList;
import java.util.Map;

public interface IListResource {

    int getResourceSize();

    Map<String, ArrayList> getResourceTextMap();

    void setResourceTextMap();

    ArrayList<?> getResourceList();

    void setResourceList(ArrayList<?> resourceList);

    ArrayList<?> getColorList();

}
