package com.siehuai.smartdrugbox.controller.Pharmacy;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.data.IRemoteDbData;
import com.siehuai.smartdrugbox.data.Pharmacy.P_MedicineDetails;

public class P_MedicineDetailsRemoteHelper extends RemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;


    public P_MedicineDetailsRemoteHelper() {
        super();
        mDatabase = getDatabaseObj();
        mOnCompleteListener = returnDefaultOnCompleteListener();
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
        if (listener != null){
           mOnCompleteListener = listener;
        }
    }

    public void insert(P_MedicineDetails medicineDetails) {
        DatabaseReference newRef = mDatabase.push();
        String key = newRef.getKey();
        medicineDetails.setId(key);
        mDatabase.child("Pharmacy Medicine Details").child(key).setValue(medicineDetails, mOnCompleteListener);
    }

    @Override
    public void insert(IRemoteDbData iRemoteDbData) {
        DatabaseReference newRef = mDatabase.push();
        String key = newRef.getKey();
        iRemoteDbData.setId(key);
        mDatabase.child("User Medicine Details").child(key).setValue(iRemoteDbData, mOnCompleteListener);
    }

    @Override
    public void delete(IRemoteDbData iRemoteDbData) {

    }

    @Override
    public void update(IRemoteDbData iRemoteDbData) {

    }

    @Override
    public void read() {

    }
}
