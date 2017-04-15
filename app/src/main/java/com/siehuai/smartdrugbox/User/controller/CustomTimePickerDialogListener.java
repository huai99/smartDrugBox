package com.siehuai.smartdrugbox.User.controller;

import android.app.TimePickerDialog;
import android.widget.TimePicker;


public class CustomTimePickerDialogListener implements TimePickerDialog.OnTimeSetListener {

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        view.setCurrentHour(hourOfDay);
        view.setCurrentMinute(minute);
    }
}
