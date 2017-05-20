package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;

public abstract class UserRemoteDbHelper extends RemoteDbHelper {

    @Override
    protected DatabaseReference getDatabaseObj() {
        //TODO:Hardcoded username for now
        return super.getDatabaseObj().child("User/Sie Huai");
    }
}
