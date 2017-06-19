package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.data.Role;

public abstract class PharmacyRemoteDbHelper extends RemoteDbHelper {
    @Override
    protected DatabaseReference getDatabaseObj() {
        //TODO:Hardcoded username for now
        return super.getDatabaseObj().child("Pharmacy").child(Role.PHARMACY_USERNAME);
    }
}
