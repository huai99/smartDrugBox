package com.siehuai.smartdrugbox.User.controller;

import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class U_DependencyInjectionModule {

    @Provides
    static MedicineOrderRemoteHelper provideMedicineOrderRemoteHelper() {
        return MedicineOrderRemoteHelper.getInstance();
    }

}
