package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnCompleteListener;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.data.AlarmData;

import java.util.Iterator;
import java.util.Map;

public class U_AlarmRemoteHelper extends UserRemoteDbHelper {
    DatabaseReference mDatabase;
    private DatabaseReference.CompletionListener mOnCompleteListener;
    private Map<String, IDbData> alarmDataMap = dataMap;
    private static U_AlarmRemoteHelper instance;
    private String key;

    public U_AlarmRemoteHelper() {
        mDatabase = getDatabaseObj().child(DataType.Alarm);
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
        if (iDbData.getId() == null) {
            key = newRef.getKey();
            iDbData.setId(getKey());
        }
        mDatabase.child(iDbData.getId()).setValue(iDbData);
    }

    @Override
    public void delete(IDbData iDbData) {
        String key = iDbData.getId();
        mDatabase.child(key).removeValue(mOnCompleteListener);
    }

    public void delete(IDbData iDbData, final IDbOnCompleteListener listener) {
        String key = iDbData.getId();
        mDatabase.child(key).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onComplete(databaseError);
            }
        });
    }

    @Override
    public void update(IDbData iDbData) {
        String id = iDbData.getId();
        mDatabase.child(id)
                .setValue(iDbData);
    }

    private void read(Iterator<?> iterator) {
        alarmDataMap.clear();
        while (iterator.hasNext()) {
            IDbData value = (AlarmData) iterator.next();
            String key = value.getId();
            alarmDataMap.put(key, value);
        }
        setChanged();
        notifyObservers(alarmDataMap.values());
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

        Iterator<AlarmData> alarmDataIterator
                = FireBaseUtils.convertDataSnapshotIterator(iterator, AlarmData.class);
        read(alarmDataIterator);
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

    public static U_AlarmRemoteHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new U_AlarmRemoteHelper();
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
