package com.siehuai.smartdrugbox.controller.User.Service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.NOTIFICATION_SERVICE;


public class NotificationService {

    NotificationManager mNmanager;
    Notification mNotification;
    Intent mIntent;
    Context mContext;

    public NotificationService(Context context) {
        mContext = context;
        mNmanager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
    }


    @TargetApi(16)
    public void createNotification(Context context,
                                   String title,
                                   String bodyText,
                                   PendingIntent contentIntent,
                                   boolean autoCancel,
                                   int iconResId
    ) {
        mNotification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setContentIntent(contentIntent)
                .setAutoCancel(autoCancel)
                .setSmallIcon(iconResId)
                .build();
    }

    public void dispatchNotification() {
        mNmanager.notify((int) Math.random(), mNotification);
    }
}
