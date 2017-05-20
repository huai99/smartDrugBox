package com.siehuai.smartdrugbox.Generic.controller.FirebaseService;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.FirebaseSetInstanceIDDbHelper;
import com.siehuai.smartdrugbox.Generic.data.FirebaseRegistrationToken;

import javax.inject.Inject;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    private FirebaseSetInstanceIDDbHelper mDbHelper;

    @Inject
    public MyFirebaseInstanceIDService(FirebaseSetInstanceIDDbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        FirebaseRegistrationToken registrationToken = new FirebaseRegistrationToken(token);
        mDbHelper.update(registrationToken);
    }

}
