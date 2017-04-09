package com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public abstract class RemoteDbHelper {

    protected enum DbStatus {
        SUCCESSFUL,
        FAIL
    }

    private DatabaseReference mDatabase;


    public RemoteDbHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    protected DatabaseReference getDatabaseObj() {
        return mDatabase;
    }

    public void attachOnCompleteListener(DatabaseReference.CompletionListener listener){}
    
    protected DatabaseReference.CompletionListener returnDefaultOnCompleteListener() {
        return new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Log.d("RemoteDbHelper", databaseError.toString());
                } else {
                    Log.d("RemoteDbHelper", "Data insert Successfully");
                }
            }
        };
    }



}
