package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.data.IRemoteDbData;

public class MedicineDetailsRemoteHelper extends RemoteDbHelper {

    private DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;

    public MedicineDetailsRemoteHelper() {
        super();
        mDatabase = getDatabaseObj();
        mOnCompleteListener = returnDefaultOnCompleteListener();
    }

    @Override
    protected DatabaseReference getDatabaseObj() {
        return super.getDatabaseObj();
    }

    @Override
    public void attachOnCompleteListener(DatabaseReference.CompletionListener listener) {
        if (listener == null) {
            mOnCompleteListener = returnDefaultOnCompleteListener();
        } else {
            mOnCompleteListener = listener;
        }
    }

    public void insert(IRemoteDbData iRemoteDbData) {
        DatabaseReference newRef = mDatabase.push();
        String key = newRef.getKey();
        iRemoteDbData.setId(key);
        mDatabase.child("User Medicine Details").child(key).setValue(iRemoteDbData, mOnCompleteListener);
    }

    @Override
    protected DatabaseReference.CompletionListener returnDefaultOnCompleteListener() {
        return super.returnDefaultOnCompleteListener();
    }

    @Override
    public void delete(IRemoteDbData dbData) {

    }

    @Override
    public void update(IRemoteDbData dbData) {

    }

    @Override
    public void read() {

    }
}
