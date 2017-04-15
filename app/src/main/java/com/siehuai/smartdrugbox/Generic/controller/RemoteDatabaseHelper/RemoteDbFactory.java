package com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper;

import com.siehuai.smartdrugbox.Pharmacy.controller.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineDetailsRemoteHelper;


public class RemoteDbFactory {
    public enum RemoteDataType {
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
