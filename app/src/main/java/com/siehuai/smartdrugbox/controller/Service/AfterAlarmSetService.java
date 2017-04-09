package com.siehuai.smartdrugbox.controller.Service;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.HardwareController.HardwareControllerImpl.BuzzerController;
import com.siehuai.smartdrugbox.view.OffAlarmActivity;

public class AfterAlarmSetService extends Service {

    MediaPlayer mMediaPlayer;
    RingtoneService mRingtoneService;
    NotificationService mNotificationService;
    BuzzerController mBuzzerController;
    Intent mIntent;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mBuzzerController = new BuzzerController(this.getApplicationContext());

        mRingtoneService = RingtoneService.getInstance();

        int alarmId = intent.getExtras().getInt("alarmId");
        mIntent = new Intent(this.getApplicationContext(), OffAlarmActivity.class);
        mIntent.putExtra("alarmId", alarmId);

        mRingtoneService.createMediaPlayer(this, R.raw.alarm_ringtone);

        createNotification(mIntent);

        String state = intent.getExtras().getString("extra");

        alarmStateHandler(state, String.valueOf(alarmId));

        return START_NOT_STICKY;
    }

    private void createNotification(Intent intent) {

        PendingIntent mPendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent}, 0);

        mNotificationService = new NotificationService(this);
        mNotificationService.createNotification(
                this, "Alarm is Ringing", "Click Me", mPendingIntent, true, R.drawable.medicine_box_icon);
    }

    private void alarmStateHandler(String state, String id) {
        assert state != null;
        switch (state) {
            case "yes":
                mRingtoneService.playRingtone();
                mNotificationService.dispatchNotification();
                mBuzzerController.turnOn();
                startActivity(mIntent);
                break;
            case "no":
                mRingtoneService.stopAndReset();
                Log.d("System Alarm", "Reach here");
                mBuzzerController.turnOff();
                break;

            default:
                Log.d("System Alarm", "Error Happens");
                break;
        }
    }
}
