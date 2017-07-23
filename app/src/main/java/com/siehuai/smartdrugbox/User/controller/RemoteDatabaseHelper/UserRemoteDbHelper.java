package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.data.Role;

public abstract class UserRemoteDbHelper extends RemoteDbHelper {

    static String user = Role.DEFAULT_PATIENT_USERNAME;

    @Override
    protected DatabaseReference getDatabaseObj() {
        //TODO:Hardcoded username for now
        user = Role.PATIENT_USERNAME;
        return super.getDatabaseObj().child("User").child(user);
    }

    public static String getCurrentUser() {
        return user;
    }

    public static boolean verifyUser() {
        return getCurrentUser().equals(Role.PATIENT_USERNAME);
    }
}
