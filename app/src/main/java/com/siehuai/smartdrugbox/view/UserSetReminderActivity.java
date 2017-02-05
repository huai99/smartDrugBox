package com.siehuai.smartdrugbox.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.CustomTimePickerDialogListener;

public class UserSetReminderActivity extends AppCompatActivity {

    TimePicker mTimePicker;
    TextView mTextView;
    FloatingActionButton mFloatingActionButton;
    AlarmDialog mAlarmDialog;
    CustomTimePickerDialogListener mCustomTimePickerDialogListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_set_reminder);

        mTextView = (TextView) findViewById(R.id.textView_reminder);

        mFloatingActionButton=(FloatingActionButton)findViewById(R.id.fab_add_alarm);

        mCustomTimePickerDialogListener = new CustomTimePickerDialogListener();

        setFloatingActionButton();

    }

    public void setFloatingActionButton(){
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlarmDialog = new AlarmDialog(UserSetReminderActivity.this,mCustomTimePickerDialogListener,5,20,true);

                mTimePicker=mAlarmDialog.getTimePicker();

                mAlarmDialog.setTextView(mTextView);

                mAlarmDialog.show();

            }
        });
    }

}
