package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class P_RemoteHelperModule {
    @Provides
    static P_MedicineDetailsRemoteHelper providePharmacyMedicineHelper() {
        return P_MedicineDetailsRemoteHelper.getInstance();
    }
}
