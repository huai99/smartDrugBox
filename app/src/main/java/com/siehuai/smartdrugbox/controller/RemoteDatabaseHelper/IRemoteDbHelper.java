package com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.data.IRemoteDbData;

public interface IRemoteDbHelper {

    /**
     * Attach onComplete listener to check the response after the server action complete
     * @param listener listener that will be called after the server side code is done
     */
    void attachOnCompleteListener(DatabaseReference.CompletionListener listener);

    void insert(IRemoteDbData dbData);

    void delete(IRemoteDbData dbData);

    void update(IRemoteDbData dbData);

    void read();
}
