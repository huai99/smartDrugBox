package com.siehuai.smartdrugbox.User.controller.FirebaseService;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;
import com.siehuai.smartdrugbox.Generic.view.MainActivity;
import com.siehuai.smartdrugbox.R;

public class U_FirebaseMessageService extends FirebaseMessagingService {

    NotificationService mNotificationService;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        createNotification(intent, remoteMessage.getNotification().getBody());
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

    private void createNotification(Intent intent, String message) {
        PendingIntent mPendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        mNotificationService = new NotificationService(this);
        mNotificationService.createNotification("Firebase message", message, mPendingIntent, true, R.drawable.camera);
    }
}
