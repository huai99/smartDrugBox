package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class P_RemoteHelperModule {
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

}
