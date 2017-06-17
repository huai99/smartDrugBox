package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.siehuai.smartdrugbox.Generic.common.FireBaseUtils;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Generic.data.Message;

import java.util.Iterator;
import java.util.Map;

public class UserMessageQueueRemoteHelper extends UserRemoteDbHelper {

    DatabaseReference mDatabase;
    private String key;
    private Map<String, IDbData> mMessageMap = dataMap;
    private static UserMessageQueueRemoteHelper instance;

    public UserMessageQueueRemoteHelper() {
        mDatabase = getDatabaseObj().child("Message-Queue");
        read();
    }

    @Override
    public void attachOnCompleteListener(DatabaseReference.CompletionListener listener) {
    }

    @Override
    public void insert(IDbData dbData) {
        DatabaseReference newRef = mDatabase.push();
        if (key == null) {
            key = newRef.getKey();
        }
        dbData.setId(getKey());
        mDatabase.child(dbData.getId()).setValue(dbData);
    }


    @Override
    public void delete(IDbData dbData) {

    }

    @Override
    public void update(IDbData dbData) {
    }

    private void read(Iterator<?> iterator) {
        mMessageMap.clear();
        while (iterator.hasNext()) {
            IDbData value = (Message) iterator.next();
            String key = value.getId();
            mMessageMap.put(key, value);
        }
        setChanged();
        notifyObservers(mMessageMap.values());
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

        Iterator<Message> messageIterator
                = FireBaseUtils.convertDataSnapshotIterator(iterator, Message.class);
        read(messageIterator);
    }

    public static UserMessageQueueRemoteHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new UserMessageQueueRemoteHelper();
                    return instance;
                } else {
                    return instance;
                }
            }
        } else {
            return instance;
        }
    }

    public String generateNewId() {
        String key = mDatabase.push().getKey();
        setKey(key);
        return key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
