package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.data.Role;

public abstract class PharmacyRemoteDbHelper extends RemoteDbHelper {

    static String pharmacy = Role.DEFAULT_PHARMACY_USERNAME;

    @Override
    protected DatabaseReference getDatabaseObj() {
        //TODO:Hardcoded username for now
        pharmacy = Role.PHARMACY_USERNAME;
        return super.getDatabaseObj().child("Pharmacy").child(pharmacy);
    }

    public static String getCurrentPharmacy() {
        return pharmacy;
    }

    public static boolean verifyPharmacy() {
        return getCurrentPharmacy().equals(Role.PHARMACY_USERNAME);
    }
}
