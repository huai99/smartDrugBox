package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.data.IDbData;

import java.util.Map;

public class MedicineDetailsRemoteHelper extends UserRemoteDbHelper {

    private DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private String key;
    private static MedicineDetailsRemoteHelper instance;
    private static Object lock = new Object();
    private Map<String, IDbData> mMedicineDetailsMap = dataMap;


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

    public void insert(IDbData iDbData) {
        DatabaseReference newRef = mDatabase.push();
        String key = newRef.getKey();
        iDbData.setId(key);
        mDatabase.child("User Medicine Details").child(key).setValue(iDbData, mOnCompleteListener);
    }

    @Override
    protected DatabaseReference.CompletionListener returnDefaultOnCompleteListener() {
        return super.returnDefaultOnCompleteListener();
    }

    @Override
    public void delete(IDbData dbData) {

    }

    @Override
    public void update(IDbData dbData) {

    }

    @Override
    public void read() {

    }
}
