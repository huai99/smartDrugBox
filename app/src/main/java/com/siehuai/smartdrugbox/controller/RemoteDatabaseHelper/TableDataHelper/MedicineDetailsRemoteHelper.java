package com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.TableDataHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.data.LocalAppData.MedicineDetails;

public class MedicineDetailsRemoteHelper extends RemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;

    public MedicineDetailsRemoteHelper() {
        super();
        mDatabase = super.getDatabaseObj();
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

    public void insert(String drugstore,
                       String medicineName,
                       int pillNumberPurchase,
                       int compartmentNumber,
                       String takeMedicineFrequency) {

//        Map<String, MedicineDetails> data = new HashMap<String, MedicineDetails>();
//        data.put(id, medicineDetails);

        DatabaseReference newRef = mDatabase.push();
        String key = newRef.getKey();
        MedicineDetails medicineDetails = new MedicineDetails(
                key, drugstore, medicineName, pillNumberPurchase, compartmentNumber, takeMedicineFrequency);
        newRef.setValue(medicineDetails, mOnCompleteListener);
    }

    @Override
    protected DatabaseReference.CompletionListener returnDefaultOnCompleteListener() {
        return super.returnDefaultOnCompleteListener();
    }
}
