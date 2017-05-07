package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper.MedicineBoxDetailsLocalDataHelper;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;

import java.util.Iterator;

public class MedicineBoxDetailsRemoteHelper extends RemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private MedicineBoxDetailsLocalDataHelper localDataHelper;
    private String key;

    public MedicineBoxDetailsRemoteHelper() {
        super();
        mDatabase = getDatabaseObj().child(DataType.MedicineBox);
        mOnCompleteListener = returnDefaultOnCompleteListener();
        localDataHelper = MedicineBoxDetailsLocalDataHelper.getInstance();
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
    public void insert(IDbData iDbData) {
        DatabaseReference newRef = mDatabase.push();
        if (key == null) {
            key = newRef.getKey();
        }
        iDbData.setId(getKey());
        mDatabase.child(DataType.MedicineBoxDetails).child(iDbData.getId()).setValue(iDbData, mOnCompleteListener);
        localDataHelper.insert(iDbData);
    }

    @Override
    public void delete(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(key).removeValue(mOnCompleteListener);
    }

    @Override
    public void update(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(key).setValue(iDbData, mOnCompleteListener);
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
                .child(DataType.MedicineBoxDetails)
                .getChildren()
                .iterator();

        Iterator<MedicineBoxDetails> medicineBoxDetailsIterator
                = FireBaseUtils.convertDataSnapshotIterator(iterator, MedicineBoxDetails.class);
        localDataHelper.read(medicineBoxDetailsIterator);
    }

    public String generateNewId() {
        DatabaseReference newRef = mDatabase.push();
        return newRef.getKey();
    }

    public String getKey() {
        String clone = String.valueOf(key);
        key = null;
        return clone;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
