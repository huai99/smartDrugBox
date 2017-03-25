package com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoteDbHelper {

    private DatabaseReference mDatabase;


    public RemoteDbHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatabaseObj(){
        return mDatabase;
    }

}
