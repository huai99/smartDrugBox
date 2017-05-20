package com.siehuai.smartdrugbox.User.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.U_FirebaseSetInstanceIDDbHelper;
import com.siehuai.smartdrugbox.Generic.data.FirebaseRegistrationToken;

public class U_BaseActivity extends AppCompatActivity {

    U_FirebaseSetInstanceIDDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseRegistrationToken token = new FirebaseRegistrationToken(FirebaseInstanceId.getInstance().getToken());
        mDbHelper = new U_FirebaseSetInstanceIDDbHelper();
        mDbHelper.update(token);
    }
}
