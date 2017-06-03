package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.Iterator;
import java.util.Map;

public class P_MedicineDetailsRemoteHelper extends PharmacyRemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private Map<String, IDbData> mMedicineDetailsMap = dataMap;
    private static P_MedicineDetailsRemoteHelper instance;

    public P_MedicineDetailsRemoteHelper() {
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
        mDatabase.child(DataType.PharmacyMedicineDetails).child(key).setValue(iDbData, mOnCompleteListener);
    }

    @Override
    public void delete(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(DataType.PharmacyMedicineDetails).child(key).removeValue(mOnCompleteListener);
    }

    @Override
    public void update(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(DataType.PharmacyMedicineDetails).child(key).setValue(iDbData, mOnCompleteListener);
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
        mMedicineDetailsMap.clear();
        while (iterator.hasNext()) {
            P_MedicineDetails value = (P_MedicineDetails) iterator.next();
            String key = value.getId();
            mMedicineDetailsMap.put(key, value);
            Log.d("P_Medicine", value.toString());
        }
        setChanged();
        notifyObservers(mMedicineDetailsMap.values());
    }

    private void transferDatatoLocal(DataSnapshot dataSnapshot) {
        Iterator<DataSnapshot> iterator = dataSnapshot.child(DataType.PharmacyMedicineDetails).getChildren().iterator();

        Iterator<P_MedicineDetails> medicineDetailsIterator
                = FireBaseUtils.convertDataSnapshotIterator(iterator, P_MedicineDetails.class);
        read(medicineDetailsIterator);
    }

    public static P_MedicineDetailsRemoteHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new P_MedicineDetailsRemoteHelper();
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
