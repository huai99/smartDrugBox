package com.siehuai.smartdrugbox.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.AlarmReceiver;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmDialog extends TimePickerDialog {

    AlarmManager mAlarmManager;
    private TimePicker mTimePicker;
    private Button onAlarmBtn, offAlarmBtn;
    private PendingIntent mPendingIntent;
    private Calendar mCalendar;
    private Intent mIntent;
    private Context mContext;
    private TextView mTextView;
    private int alarmNum;

    public AlarmDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_user_set_reminder);

        onAlarmBtn = (Button) findViewById(R.id.btn_setAlarm);

        offAlarmBtn = (Button) findViewById(R.id.btn_offAlarm);

        mTimePicker = (TimePicker) findViewById(R.id.timepicker_reminder);

        mAlarmManager = (AlarmManager) this.mContext.getSystemService(ALARM_SERVICE);

        mCalendar = Calendar.getInstance();

        mIntent = new Intent(this.mContext, AlarmReceiver.class);

        setOnAlarmBtn(mCalendar);

        turnOffAlarmBtn();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
    }

    @Override
    public void updateTime(int hourOfDay, int minuteOfHour) {
        super.updateTime(hourOfDay, minuteOfHour);
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
                mPendingIntent = PendingIntent.getBroadcast(mContext, 0 , mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
                mTextView.setText("The alarm is set: "+ hour_string + " : " + minute_string );
                dismiss();
            }
        });
    }

    private void turnOffAlarmBtn() {
        offAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("extra", "no");
                mContext.sendBroadcast(mIntent);

                if (mPendingIntent == null) {
                    Log.d("UserReminder", "This pendingIntent is null");
                }

                mAlarmManager.cancel(mPendingIntent);
                mTextView.setText("The alarm is cancel");
                dismiss();
            }
        });
    }

    public TimePicker getTimePicker() {
        return mTimePicker;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public void setTextView(TextView textView) {
        mTextView = textView;
    }

    public int getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(int alarmNum) {
        this.alarmNum = alarmNum;
    }
}
