package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Pharmacy.data.PharmacyDetails;

import java.util.Iterator;
import java.util.Map;

public class PharmacyDetailsRemoteHelper extends PharmacyRemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private Map<String, IDbData> mPharmacyDetailsMap = dataMap;
    private static PharmacyDetailsRemoteHelper instance;

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

    private void read(Iterator<?> iterator) {
        mPharmacyDetailsMap.clear();
        while (iterator.hasNext()) {
            PharmacyDetails value = (PharmacyDetails) iterator.next();
            String key = value.getId();
            mPharmacyDetailsMap.put(key, value);
            Log.d("P_Medicine", value.toString());
        }
        setChanged();
        notifyObservers(mPharmacyDetailsMap.values());
    }

    private void transferDatatoLocal(DataSnapshot dataSnapshot) {
        Iterator<DataSnapshot> iterator = dataSnapshot.child(DataType.PharmacyDetails).getChildren().iterator();

        Iterator<PharmacyDetails> pharmacyDetailsIterator
                = FireBaseUtils.convertDataSnapshotIterator(iterator, PharmacyDetails.class);
        read(pharmacyDetailsIterator);
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
