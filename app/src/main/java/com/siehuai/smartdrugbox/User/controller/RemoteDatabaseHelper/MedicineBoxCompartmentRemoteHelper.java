package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;
import com.siehuai.smartdrugbox.User.view.UserUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MedicineBoxCompartmentRemoteHelper extends UserRemoteDbHelper {

    DatabaseReference mDatabase;
    private ObjectMapper mObjectMapper = new ObjectMapper();
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private Map<String, IDbData> mMedicineBoxCompartmentMap = dataMap;
    private static MedicineBoxCompartmentRemoteHelper instance;
    private String key;
    private int FLAG_READ = 0;
    private int FLAG_FIND = 1;

    public MedicineBoxCompartmentRemoteHelper() {
        mDatabase = getDatabaseObj().child(DataType.MedicineBox).child(DataType.MedicineBoxCompartmentDetails);
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
        if (key == null) {
            key = newRef.getKey();
        }
        iDbData.setId(getKey());
        mDatabase.child(iDbData.getId()).setValue(iDbData, mOnCompleteListener);
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

    public void read(Iterator<?> iterator) {
        mMedicineBoxCompartmentMap.clear();
        while (iterator.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            MedicineBoxCompartment medicineBoxCompartment
                    = UserUtils.convertRawToMedicineCompartment(mObjectMapper, map);
            mMedicineBoxCompartmentMap.put(medicineBoxCompartment.getId(), medicineBoxCompartment);
        }
        setChanged();
        notifyObservers(mMedicineBoxCompartmentMap.values());
    }

    private MedicineBoxCompartment findOneFromRemote(Iterator<?> iterator) {
        MedicineBoxCompartment medicineBoxCompartment = null;
        while (iterator.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            medicineBoxCompartment
                    = UserUtils.convertRawToMedicineCompartment(mObjectMapper, map);
        }
        return medicineBoxCompartment;
    }

    public void find(final String id, final IDbOnDataChangeListener dataChangeListener) {
        mDatabase.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator iterator = transferDatatoLocalWithoutSerializer(dataSnapshot, FLAG_FIND);
                dataChangeListener.onDataChange(findOneFromRemote(iterator));
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
            read(mapObjectIterator);
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

    public static MedicineBoxCompartmentRemoteHelper getInstance() {
        if (verifyUser()) {
            instance = new MedicineBoxCompartmentRemoteHelper();
            return instance;
        }
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MedicineBoxCompartmentRemoteHelper();
                    return instance;
                } else {
                    return instance;
                }
            }
        } else {
            return instance;
        }
    }

    public U_AlarmRemoteHelper getAlarmRemoteHelper(CompartmentDetails compartmentDetails) {
        String medicineBoxId = compartmentDetails.getMedicineBoxId();
        String compartmentId = compartmentDetails.getId();
        return new U_AlarmRemoteHelper(medicineBoxId, compartmentId);
    }
}
