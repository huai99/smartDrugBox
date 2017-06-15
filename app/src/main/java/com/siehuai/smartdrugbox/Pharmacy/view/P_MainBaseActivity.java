package com.siehuai.smartdrugbox.Pharmacy.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.siehuai.smartdrugbox.Generic.data.FirebaseRegistrationToken;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_FirebaseSetInstanceIdDbHelper;


public class P_MainBaseActivity extends AppCompatActivity {

    P_FirebaseSetInstanceIdDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseRegistrationToken token = new FirebaseRegistrationToken(FirebaseInstanceId.getInstance().getToken());
        mDbHelper = new P_FirebaseSetInstanceIdDbHelper();
        mDbHelper.update(token);
    }
}
