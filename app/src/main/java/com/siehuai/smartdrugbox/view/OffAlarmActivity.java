package com.siehuai.smartdrugbox.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.Service.UserAlarmService;

public class OffAlarmActivity extends AppCompatActivity {

    UserAlarmService mUserAlarmService;
    boolean mAlarmStatus = true;
    TextView mTextView;
    int mAlarmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_alarm);

        mAlarmId = getIntent().getExtras().getInt("alarmId");

        mUserAlarmService = new UserAlarmService(OffAlarmActivity.this);
        mTextView = (TextView) findViewById(R.id.text_status);
        setStatusText();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_cancel_alarm);
        setFloatingActionBtnClick(fab);
    }

    public void setStatusText() {
        String status;
        status = mAlarmStatus ? "On" : "Off";
        mTextView.setText(status);
    }

    public void setFloatingActionBtnClick(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlarmStatus = !mAlarmStatus;
                setStatusText();
                mUserAlarmService.turnOffAlarmMusic();
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
