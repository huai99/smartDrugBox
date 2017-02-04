package com.siehuai.smartdrugbox.controller;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.view.UserSetReminderActivity;

public class RingtoneService extends Service {

    Intent mIntent;
    MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationManager mNmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mIntent = new Intent(this.getApplicationContext(), UserSetReminderActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{mIntent}, 0);

        Notification mNotify = new Notification.Builder(this)
                .setContentTitle("Alarm is ringing")
                .setContentText("Click Me")
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.medicine_box_icon)
                .build();

        String state = intent.getExtras().getString("extra");
        Log.d("Ringtone Service",state);

        assert state != null;
        switch (state) {
            case "yes":
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_ringtone);
                mMediaPlayer.start();
                mNmanager.notify(0, mNotify);
                break;
            case "no":
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                break;
            default:
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                break;
        }


        return START_NOT_STICKY;
    }
}
