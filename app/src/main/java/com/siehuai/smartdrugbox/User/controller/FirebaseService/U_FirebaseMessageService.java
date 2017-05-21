package com.siehuai.smartdrugbox.User.controller.FirebaseService;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.controller.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;
import com.siehuai.smartdrugbox.Generic.view.MainActivity;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;

import java.util.HashMap;
import java.util.Map;

public class U_FirebaseMessageService extends FirebaseMessagingService {

    NotificationService mNotificationService;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String id = remoteMessage.getData().get("id");
        String medicineBoxId = remoteMessage.getData().get("medicineBoxId");
        MedicineBoxCompartmentRemoteHelper remoteHelper = new MedicineBoxCompartmentRemoteHelper();
        remoteHelper.find(medicineBoxId, new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                MedicineBoxCompartment medicineBoxCompartment = (MedicineBoxCompartment) data;
                Log.d("Firebase Message",medicineBoxCompartment.toString());
            }
        });
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        createNotification(intent, remoteMessage);
        Log.d("Firebase Message", remoteMessage.getNotification().getBody());
        mNotificationService.dispatchNotification();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    private void createNotification(Intent intent, RemoteMessage message) {
        PendingIntent mPendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        mNotificationService = new NotificationService(this);
        String title = message.getNotification().getTitle();
        String content = message.getNotification().getBody();
        mNotificationService.createNotification(title, content, mPendingIntent, true, R.drawable.camera);
    }
}
