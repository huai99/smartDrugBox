package com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper;

import com.siehuai.smartdrugbox.Generic.controller.Adapter.ListAdapterImpl.MessageQueueListAdapter;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Generic.data.ListResource.ListResourceImpl.MessageQueueListResource;
import com.siehuai.smartdrugbox.Pharmacy.controller.P_OrderQueueListAdapter;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MessageQueueRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_OrderQueueListRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.PharmacyDetailsRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.data.ListResource.P_OrderQueueListResource;

import dagger.Module;
import dagger.Provides;

@Module
public class P_DependencyInjectionModule {
/*
    RemoteDbHelper
 */

    @Provides
    static P_MedicineDetailsRemoteHelper providePharmacyMedicineHelper() {
        return P_MedicineDetailsRemoteHelper.getInstance();
    }

    @Provides
    static PharmacyDetailsRemoteHelper providePharmacyDetailsHelper() {
        return PharmacyDetailsRemoteHelper.getInstance();
    }

    @Provides
    static MedicineOrderRemoteHelper provideMedicineOrderRemoteHelper() {
        return MedicineOrderRemoteHelper.getInstance();
    }

    @Provides
    static P_OrderQueueListRemoteHelper provideOrderQueueListRemoteHelper() {
        return P_OrderQueueListRemoteHelper.getInstance();
    }

    @Provides
    static P_MessageQueueRemoteHelper provideMessageQueueRemoteHelper() {
        return P_MessageQueueRemoteHelper.getInstance();
    }

/*
    Adapter
 */

    @Provides
    P_OrderQueueListAdapter provideOrderQueueListAdapter() {
        return new P_OrderQueueListAdapter();
    }

    @Provides
    static MessageQueueListAdapter provideMessageQueueListAdapter() {
        return new MessageQueueListAdapter();
    }

    /*
    Resource
 */
    @Provides
    static P_OrderQueueListResource provideOrderQueueListResource() {
        return new P_OrderQueueListResource();
    }


    @Provides
    static MessageQueueListResource provideMessageQueueListResource() {
        return new MessageQueueListResource();
    }

}
