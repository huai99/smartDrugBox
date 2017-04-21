package com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.data.IDbData;

public interface IRemoteDbHelper {

    /**
     * Attach onComplete listener to check the response after the server action complete
     * @param listener listener that will be called after the server side code is done
     */
    void attachOnCompleteListener(DatabaseReference.CompletionListener listener);

    void insert(IDbData dbData);

    void delete(IDbData dbData);

    void update(IDbData dbData);

    void read();
}
