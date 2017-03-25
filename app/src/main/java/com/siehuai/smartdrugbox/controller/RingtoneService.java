package com.siehuai.smartdrugbox.controller;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.HardwareController.HardwareControllerImpl.BuzzerController;
import com.siehuai.smartdrugbox.view.OffAlarmActivity;

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

        BuzzerController buzzerController = new BuzzerController(this.getApplicationContext());

        int alarmId = intent.getExtras().getInt("alarmId");

        mIntent = new Intent(this.getApplicationContext(), OffAlarmActivity.class);
        mIntent.putExtra("alarmId", alarmId);
        PendingIntent mPendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{mIntent}, 0);

        NotificationService notificationService = new NotificationService(this);
        notificationService.createNotification(this,
                "Alarm is Ringing",
                "Click Me", mPendingIntent,
                true,
                R.drawable.medicine_box_icon);

        String state = intent.getExtras().getString("extra");

        assert state != null;
        switch (state) {
            case "yes":
                mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_ringtone);
                mMediaPlayer.start();
                notificationService.dispatchNotification();
                buzzerController.turnOn();
                break;
            case "no":
                if (mMediaPlayer != null) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }

                buzzerController.turnOff();

                break;
            default:
                if (mMediaPlayer != null) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }
                break;
        }


        return START_NOT_STICKY;
    }
}
