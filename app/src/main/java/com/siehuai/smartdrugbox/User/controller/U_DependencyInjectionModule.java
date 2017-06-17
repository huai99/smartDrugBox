package com.siehuai.smartdrugbox.User.controller;

import com.siehuai.smartdrugbox.Generic.controller.Adapter.ListAdapterImpl.MessageQueueListAdapter;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Generic.data.ListResource.ListResourceImpl.MessageQueueListResource;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.UserMessageQueueRemoteHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class U_DependencyInjectionModule {
    /*
        Remote Helper
     */

    @Provides
    static MedicineOrderRemoteHelper provideMedicineOrderRemoteHelper() {
        return MedicineOrderRemoteHelper.getInstance();
    }

    @Provides
    static UserMessageQueueRemoteHelper provideUserMessageQueueRemoteHelper() {
        return UserMessageQueueRemoteHelper.getInstance();
    }


    /*
        Adapter
     */

    @Provides
    static MessageQueueListAdapter provideMessageQueueListAdapter() {
        return new MessageQueueListAdapter();
    }


    /*
        Resource
     */

    @Provides
    static MessageQueueListResource provideMessageQueueListResource() {
        return new MessageQueueListResource();
    }

}
