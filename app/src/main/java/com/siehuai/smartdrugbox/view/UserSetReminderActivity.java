package com.siehuai.smartdrugbox.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TimePicker;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.CustomTimePickerDialogListener;
import com.siehuai.smartdrugbox.controller.ReminderListViewAdapter;

import java.util.ArrayList;

public class UserSetReminderActivity extends AppCompatActivity {

    TimePicker mTimePicker;
    FloatingActionButton mFloatingActionButton;
    AlarmDialog mAlarmDialog;
    CustomTimePickerDialogListener mCustomTimePickerDialogListener;
    ExpandableListView mExpandableListView;

    ArrayList<String> testingList = new ArrayList<String>() {
        {
            add("Testing 1");
            add("Testing 2");
            add("Testing 3");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_set_reminder);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_alarm);

        mExpandableListView = (ExpandableListView) findViewById(R.id.expandList_reminder);

        ReminderListViewAdapter reminderListViewAdapter = new ReminderListViewAdapter(this,mExpandableListView,testingList);

        mExpandableListView.setAdapter(reminderListViewAdapter);

        mCustomTimePickerDialogListener = new CustomTimePickerDialogListener();

        setFloatingActionButton();

    }

    public void setFloatingActionButton() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlarmDialog = new AlarmDialog(UserSetReminderActivity.this, mCustomTimePickerDialogListener, 5, 20, true);

                mTimePicker = mAlarmDialog.getTimePicker();

                mAlarmDialog.show();

            }
        });
    }

}
