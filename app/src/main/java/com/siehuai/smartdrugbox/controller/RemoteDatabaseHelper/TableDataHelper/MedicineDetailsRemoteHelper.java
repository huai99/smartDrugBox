package com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.TableDataHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.data.medicineDetailsData.MedicineDetails;

public class MedicineDetailsRemoteHelper extends RemoteDbHelper {

    DatabaseReference mDatabase;

    public MedicineDetailsRemoteHelper() {
        super();
        mDatabase = super.getDatabaseObj();
    }

    @Override
    public DatabaseReference getDatabaseObj() {
        return super.getDatabaseObj();
    }

    public void insert(String id,
                       String drugstore,
                       String medicineName,
                       int pillNumberPurchase,
                       int compartmentNumber,
                       String takeMedicineFrequency) {
        MedicineDetails medicineDetails = new MedicineDetails(
                id,drugstore,medicineName,pillNumberPurchase,compartmentNumber,takeMedicineFrequency);
        mDatabase.child("Medicine Details").setValue(medicineDetails);
    }


}
