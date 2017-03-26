package com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseConnectionHelper {

    boolean connectionChecker;

    public DatabaseConnectionHelper() {
        final DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                connectionChecker = snapshot.getValue(Boolean.class);
                if (connectionChecker) {
                    Log.d("Remote", "Connected");
                } else {
                    Log.d("Remote", "Not Connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public boolean isConnectionChecker() {
        Log.d("Remote","Connection Checker: "+ String.valueOf(connectionChecker));
        return connectionChecker;
    }
}
