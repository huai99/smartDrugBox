package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;

import java.util.Iterator;
import java.util.Map;

public class P_OrderQueueListRemoteHelper extends PharmacyRemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private String key;
    private Map<String, IDbData> mMedicineOrderMap = dataMap;
    private static P_OrderQueueListRemoteHelper instance;

    public P_OrderQueueListRemoteHelper() {
        mDatabase = getDatabaseObj().child("Order-Queue");
        read();
    }

    @Override
    public void attachOnCompleteListener(DatabaseReference.CompletionListener listener) {
        if (listener != null) {
            mOnCompleteListener = listener;
        }
    }

    @Override
    public void insert(IDbData dbData) {
    }


    @Override
    public void delete(IDbData dbData) {

    }

    @Override
    public void update(IDbData dbData) {
    }

    private void read(Iterator<?> iterator) {
        mMedicineOrderMap.clear();
        while (iterator.hasNext()) {
            IDbData value = (MedicineOrder) iterator.next();
            String key = value.getId();
            mMedicineOrderMap.put(key, value);
            Log.d("Medicine Box Details", value.toString());
        }
        setChanged();
        notifyObservers(mMedicineOrderMap.values());
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

    private void transferDatatoLocal(DataSnapshot dataSnapshot) {
        Iterator<DataSnapshot> iterator = dataSnapshot
                .getChildren()
                .iterator();

        Iterator<MedicineOrder> medicineOrderIterator
                = FireBaseUtils.convertDataSnapshotIterator(iterator, MedicineOrder.class);
        read(medicineOrderIterator);
    }

    public static P_OrderQueueListRemoteHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new P_OrderQueueListRemoteHelper();
                    return instance;
                } else {
                    return instance;
                }
            }
        } else {
            return instance;
        }
    }

    public String generateNewId() {
        String key = mDatabase.push().getKey();
        setKey(key);
        return key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
