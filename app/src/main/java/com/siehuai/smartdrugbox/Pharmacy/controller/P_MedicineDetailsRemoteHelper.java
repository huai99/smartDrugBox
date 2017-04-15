package com.siehuai.smartdrugbox.Pharmacy.controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IRemoteDbData;
import com.siehuai.smartdrugbox.Pharmacy.controller.LocalAppDataHelper.P_MedicineDetailsLocalDataHelper;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.Iterator;

public class P_MedicineDetailsRemoteHelper extends RemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    P_MedicineDetailsLocalDataHelper localDataHelper;


    public P_MedicineDetailsRemoteHelper() {
        super();
        mDatabase = getDatabaseObj();
        mOnCompleteListener = returnDefaultOnCompleteListener();
        localDataHelper = P_MedicineDetailsLocalDataHelper.getInstance();
    }

    @Override
    protected DatabaseReference getDatabaseObj() {
        return super.getDatabaseObj();
    }

    @Override
    protected DatabaseReference.CompletionListener returnDefaultOnCompleteListener() {
        return super.returnDefaultOnCompleteListener();
    }

    @Override
    public void attachOnCompleteListener(DatabaseReference.CompletionListener listener) {
        if (listener != null) {
            mOnCompleteListener = listener;
        }
    }

    @Override
    public void insert(IRemoteDbData iRemoteDbData) {
        DatabaseReference newRef = mDatabase.push();
        String key = newRef.getKey();
        iRemoteDbData.setId(key);
        mDatabase.child(DataType.PharmacyMedicineDetails).child(key).setValue(iRemoteDbData, mOnCompleteListener);
        localDataHelper.insert(iRemoteDbData);
    }

    @Override
    public void delete(IRemoteDbData iRemoteDbData) {

    }

    @Override
    public void update(IRemoteDbData iRemoteDbData) {

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
        Iterator<DataSnapshot> iterator = dataSnapshot.child(DataType.PharmacyMedicineDetails).getChildren().iterator();

        Iterator<P_MedicineDetails> medicineDetailsIterator
                = FireBaseUtils.convertDataSnapshotIterator(iterator, P_MedicineDetails.class);
        localDataHelper.read(medicineDetailsIterator);
    }
}
