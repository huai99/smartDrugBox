package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Generic.data.PharmacyDetails;

import java.util.Observable;
import java.util.Observer;

public class PharmacyDetailsRemoteHelper extends PharmacyRemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private static PharmacyDetailsRemoteHelper instance;
    PharmacyDetails mPharmacyDetails;

    public PharmacyDetailsRemoteHelper() {
        super();
        mDatabase = getDatabaseObj();
        mOnCompleteListener = returnDefaultOnCompleteListener();
        read();
    }

    @Override
    public void attachOnCompleteListener(DatabaseReference.CompletionListener listener) {
        if (listener != null) {
            mOnCompleteListener = listener;
        }
    }

    @Override
    public void insert(IDbData iDbData) {
        DatabaseReference newRef = mDatabase.push();
        String key = newRef.getKey();
        iDbData.setId(key);
        mDatabase.child(DataType.PharmacyDetails).setValue(iDbData, mOnCompleteListener);
    }

    @Override
    public void delete(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(DataType.PharmacyDetails).removeValue(mOnCompleteListener);
    }

    @Override
    public void update(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(DataType.PharmacyDetails).setValue(iDbData, mOnCompleteListener);
    }

    @Override
    public void read() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                transferDatatoLocal(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void findAll(final IDbOnDataChangeListener listener) {
        final Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                listener.onDataChange(arg);
            }
        };
        addObserver(observer);
        setChanged();
        notifyObservers(mPharmacyDetails);
    }

    private void transferDatatoLocal(DataSnapshot dataSnapshot) {
        mPharmacyDetails = dataSnapshot.child(DataType.PharmacyDetails).getValue(PharmacyDetails.class);
        setChanged();
        notifyObservers(mPharmacyDetails);
    }

    public static PharmacyDetailsRemoteHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new PharmacyDetailsRemoteHelper();
                    return instance;
                } else {
                    return instance;
                }
            }
        } else {
            return instance;
        }
    }

}
