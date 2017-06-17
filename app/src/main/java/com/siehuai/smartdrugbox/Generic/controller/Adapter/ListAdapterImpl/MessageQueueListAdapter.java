package com.siehuai.smartdrugbox.Generic.controller.Adapter.ListAdapterImpl;

import com.siehuai.smartdrugbox.Generic.controller.Adapter.GenericIListAdapter;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;

public class MessageQueueListAdapter extends GenericIListAdapter {
    @Override
    public void setResourceArrayList(IListResource listResource) {
        mIListResource = listResource;
    }
}
