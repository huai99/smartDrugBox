package com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.data.DataType;
import com.siehuai.smartdrugbox.Generic.data.FirebaseRegistrationToken;
import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class U_FirebaseSetInstanceIDDbHelper extends UserRemoteDbHelper {

    DatabaseReference mDatabase;

    public U_FirebaseSetInstanceIDDbHelper() {
        mDatabase = getDatabaseObj();
    }

    @Override
    public void attachOnCompleteListener(DatabaseReference.CompletionListener listener) {

    }

    @Override
    public void insert(IDbData dbData) {
    }

    @Override
    public void delete(IDbData dbData) {

    }

    @Override
    public void update(IDbData dbData) {
        FirebaseRegistrationToken token = (FirebaseRegistrationToken) dbData;
        mDatabase.child(DataType.RegistrationToken).setValue(token.getRegistrationToken());
    }

    @Override
    public void read() {

    }
}
