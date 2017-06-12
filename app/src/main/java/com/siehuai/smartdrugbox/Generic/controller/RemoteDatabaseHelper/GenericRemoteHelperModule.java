package com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class GenericRemoteHelperModule {

    @Provides
    static MedicineOrderRemoteHelper providePharmacyMedicineHelper() {
        return MedicineOrderRemoteHelper.getInstance();
    }
}
