package com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper;

import com.siehuai.smartdrugbox.controller.Pharmacy.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.controller.User.RemoteDatabaseHelper.MedicineDetailsRemoteHelper;


public class RemoteDbFactory {
    public static enum RemoteDataType {
        PharmacyMedicineDetails,
        UserMedicineDetails
    }

    public static RemoteDbHelper createRemoteDbHelper(RemoteDataType dataType) {
        if (dataType == RemoteDataType.PharmacyMedicineDetails) {
            return new P_MedicineDetailsRemoteHelper();
        } else {
            return new MedicineDetailsRemoteHelper();
        }
    }
}
