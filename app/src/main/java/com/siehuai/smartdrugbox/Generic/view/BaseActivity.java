package com.siehuai.smartdrugbox.Generic.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.FirebaseSetInstanceIDDbHelper;
import com.siehuai.smartdrugbox.Generic.data.FirebaseRegistrationToken;

public class BaseActivity extends AppCompatActivity {

    FirebaseSetInstanceIDDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseRegistrationToken token = new FirebaseRegistrationToken(FirebaseInstanceId.getInstance().getToken());
        mDbHelper = new FirebaseSetInstanceIDDbHelper();
        mDbHelper.update(token);
    }
}
