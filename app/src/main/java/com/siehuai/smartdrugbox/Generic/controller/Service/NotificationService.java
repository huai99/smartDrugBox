package com.siehuai.smartdrugbox.Generic.controller.Service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.NOTIFICATION_SERVICE;


public class NotificationService {

    private NotificationManager mNmanager;
    private Notification mNotification;
    private Intent mIntent;
    private Context mContext;

    public NotificationService(Context context) {
        mContext = context;
        mNmanager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
    }


    @TargetApi(16)
    public void createNotification(String title,
                                   String bodyText,
                                   PendingIntent contentIntent,
                                   boolean autoCancel,
                                   int iconResId
    ) {
        mNotification = new Notification.Builder(mContext)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setContentIntent(contentIntent)
                .setAutoCancel(autoCancel)
                .setSmallIcon(iconResId)
                .build();
    }

    public void dispatchNotification() {
        mNmanager.notify(0, mNotification);
    }
}
