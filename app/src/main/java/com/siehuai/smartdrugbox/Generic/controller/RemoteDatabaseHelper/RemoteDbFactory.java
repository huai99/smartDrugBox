package com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper;

import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxDetailsRemoteHelper;


public class RemoteDbFactory {
    public enum RemoteDataType {
        PharmacyMedicineDetails,
        UserMedicineDetails,
        MedicineBoxDetails,
        CompartmentDetails
    }

    public static RemoteDbHelper createRemoteDbHelper(RemoteDataType dataType) {
        if (dataType == RemoteDataType.PharmacyMedicineDetails) {
            return P_MedicineDetailsRemoteHelper.getInstance();
        } else if (dataType == RemoteDataType.CompartmentDetails) {
            return MedicineBoxCompartmentRemoteHelper.getInstance();
        } else {
            return MedicineBoxDetailsRemoteHelper.getInstance();
        }
    }
}
