package com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper;

import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.PharmacyDetailsRemoteHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class P_DependencyInjectionModule {
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
