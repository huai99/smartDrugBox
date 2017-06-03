package com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.siehuai.smartdrugbox.Generic.data.IDbData;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


public abstract class RemoteDbHelper extends Observable implements IRemoteDbHelper {

    private DatabaseReference mDatabase;

    protected static Object lock = new Object();

    protected Map<String, IDbData> dataMap = new HashMap<>();

    protected RemoteDbHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    protected DatabaseReference getDatabaseObj() {
        return mDatabase;
    }

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

    public void findAll(final IDbOnDataChangeListener listener) {
        final Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                listener.onDataChange(arg);
            }
        };
        addObserver(observer);
        setChanged();
        notifyObservers(dataMap.values());
    }

}
