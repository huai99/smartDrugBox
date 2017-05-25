package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper.MedicineBoxCompartmentLocalDataHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MedicineBoxCompartmentRemoteHelper extends UserRemoteDbHelper {

    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private MedicineBoxCompartmentLocalDataHelper localDataHelper;
    private String key;
    private int FLAG_READ = 0;
    private int FLAG_FIND = 1;

    public MedicineBoxCompartmentRemoteHelper() {
        super();
        mDatabase = getDatabaseObj().child(DataType.MedicineBox).child(DataType.MedicineBoxCompartmentDetails);
        mOnCompleteListener = returnDefaultOnCompleteListener();
        localDataHelper = MedicineBoxCompartmentLocalDataHelper.getInstance();
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
        mDatabase.child(iDbData.getId()).setValue(iDbData, mOnCompleteListener);
        localDataHelper.insert(iDbData);
    }

    @Override
    public void delete(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(key).removeValue(mOnCompleteListener);
    }

    @Override
    public void update(IDbData iDbData) {
        CompartmentDetails compartmentDetails = (CompartmentDetails) iDbData;
        String medicineBoxId = compartmentDetails.getMedicineBoxId();
        String id = compartmentDetails.getId();
        mDatabase.child(medicineBoxId)
                .child("compartmentDetailsMap")
                .child("Compartment " + id)
                .setValue(compartmentDetails, mOnCompleteListener);
    }

    @Override
    public void read() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                transferDatatoLocalWithoutSerializer(dataSnapshot, FLAG_READ);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void find(final String id, final IDbOnDataChangeListener dataChangeListener) {
        mDatabase.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator iterator = transferDatatoLocalWithoutSerializer(dataSnapshot, FLAG_FIND);
                dataChangeListener.onDataChange(localDataHelper.findOneFromRemote(iterator));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    TODO:Another function to handle single value instead of using if-else
    private Iterator<Map<String, Object>> transferDatatoLocalWithoutSerializer(DataSnapshot dataSnapshot, int flag) {
        Iterator<Map<String, Object>> mapObjectIterator = null;
        if (flag == FLAG_READ) {
            Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
            mapObjectIterator
                    = FireBaseUtils.SpecialConvertDataSnapshotIterator(iterator);
            localDataHelper.read(mapObjectIterator);
            return mapObjectIterator;
        } else {
            ArrayList<DataSnapshot> list = new ArrayList<>();
            list.add(dataSnapshot);
            mapObjectIterator = FireBaseUtils.SpecialConvertDataSnapshotIterator(list.iterator());
            return mapObjectIterator;
        }
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
