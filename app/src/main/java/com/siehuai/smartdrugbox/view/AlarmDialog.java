package com.siehuai.smartdrugbox.view;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.AlarmReceiver;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmDialog extends TimePickerDialog {

    AlarmManager mAlarmManager;
    private TimePicker mTimePicker;
    private Button okBtn, cancelButton;
    private PendingIntent mPendingIntent;
    private Calendar mCalendar;
    private Intent mIntent;
    private Context mContext;
    private int alarmNum;

    public AlarmDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_user_set_reminder);

        okBtn = (Button) findViewById(R.id.btn_ok);

        cancelButton = (Button) findViewById(R.id.btn_cancel);

        mTimePicker = (TimePicker) findViewById(R.id.timepicker_reminder);

        mAlarmManager = (AlarmManager) this.mContext.getSystemService(ALARM_SERVICE);

        mCalendar = Calendar.getInstance();

        mIntent = new Intent(this.mContext, AlarmReceiver.class);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
    }

    @Override
    public void updateTime(int hourOfDay, int minuteOfHour) {
        super.updateTime(hourOfDay, minuteOfHour);
    }

    @TargetApi(22)
    protected void setConfirmBtn(final Calendar calendar) {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting the alarm time to the timepicker time
                calendar.set(Calendar.HOUR_OF_DAY, mTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, mTimePicker.getCurrentMinute());

                //Pass in the state of the request, yes for activate alarm
                mIntent.putExtra("extra", "yes");
                mPendingIntent = PendingIntent.getBroadcast(mContext, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
                dismiss();
            }
        });
    }

    public TimePicker getTimePicker() {
        return mTimePicker;
    }

    public Button getOkBtn() {
        return okBtn;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
