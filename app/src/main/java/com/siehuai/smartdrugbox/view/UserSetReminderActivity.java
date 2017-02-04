package com.siehuai.smartdrugbox.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.AlarmReceiver;

import java.util.Calendar;

public class UserSetReminderActivity extends AppCompatActivity {

    AlarmManager mAlarmManager;
    private TimePicker mTimePicker;
    private TextView alarmTextView;
    private Button onAlarmBtn, offAlarmBtn;
    private PendingIntent mPendingIntent;
    private Calendar mCalendar;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_set_reminder);

        alarmTextView = (TextView) findViewById(R.id.text_alarm);

        onAlarmBtn = (Button) findViewById(R.id.btn_setAlarm);

        offAlarmBtn = (Button) findViewById(R.id.btn_offAlarm);

        mTimePicker = (TimePicker) findViewById(R.id.timepicker_reminder);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        mCalendar = Calendar.getInstance();

        mIntent = new Intent(UserSetReminderActivity.this, AlarmReceiver.class);

        setOnAlarmBtn(mCalendar);

        turnOffAlarmBtn();
    }

    protected void setOnAlarmBtn(final Calendar calendar) {
        onAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting the alarm time to the timepicker time
                calendar.set(Calendar.HOUR_OF_DAY, mTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, mTimePicker.getCurrentMinute());

                String hour_string = String.valueOf(mTimePicker.getCurrentHour());
                String minute_string = String.valueOf(mTimePicker.getCurrentMinute());

                //Pass in the state of the request, yes for activate alarm
                mIntent.putExtra("extra", "yes");
                mPendingIntent = PendingIntent.getBroadcast(UserSetReminderActivity.this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
                alarmTextView.setText("Current Alarm :" + hour_string + " : " + minute_string);
            }
        });
    }

    protected void turnOffAlarmBtn() {
        offAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("extra", "no");
                sendBroadcast(mIntent);
                alarmTextView.setText("Alarm clock cancel");

                if (mPendingIntent == null) {
                    Log.d("UserReminder", "This pendingIntent is null");
                }

                mAlarmManager.cancel(mPendingIntent);
            }
        });
    }

}
