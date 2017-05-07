package com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper;

import com.siehuai.smartdrugbox.Pharmacy.controller.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxDetailsRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineDetailsRemoteHelper;


public class RemoteDbFactory {
    public enum RemoteDataType {
        PharmacyMedicineDetails,
        UserMedicineDetails,
        MedicineBoxDetails,
        CompartmentDetails
    }

    public static RemoteDbHelper createRemoteDbHelper(RemoteDataType dataType) {
        if (dataType == RemoteDataType.PharmacyMedicineDetails) {
            return new P_MedicineDetailsRemoteHelper();
        } else if (dataType == RemoteDataType.UserMedicineDetails) {
            return new MedicineDetailsRemoteHelper();
        } else if (dataType == RemoteDataType.CompartmentDetails) {
            return new MedicineBoxCompartmentRemoteHelper();
        } else {
            return new MedicineBoxDetailsRemoteHelper();
        }
    }
}
