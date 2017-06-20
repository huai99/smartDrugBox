package com.siehuai.smartdrugbox.User.view;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TimePicker;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.Adapter.ReminderListViewAdapter;
import com.siehuai.smartdrugbox.User.controller.CustomTimePickerDialogListener;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.U_AlarmRemoteHelper;
import com.siehuai.smartdrugbox.User.data.AlarmData;

import java.util.Collection;

public class UserSetReminderActivity extends AppCompatActivity {

    FloatingActionButton mFloatingActionButton;
    ReminderListViewAdapter reminderListViewAdapter;
    AlarmDialog mAlarmDialog;
    CustomTimePickerDialogListener mCustomTimePickerDialogListener;
    ExpandableListView mExpandableListView;
    U_AlarmRemoteHelper mAlarmRemoteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_set_reminder);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_alarm);

        mExpandableListView = (ExpandableListView) findViewById(R.id.expandList_reminder);

        reminderListViewAdapter = new ReminderListViewAdapter(this, mExpandableListView);

        mAlarmRemoteHelper = U_AlarmRemoteHelper.getInstance();

        setupAlarmDataResource();

        mExpandableListView.setAdapter(reminderListViewAdapter);

        mCustomTimePickerDialogListener = new CustomTimePickerDialogListener();

        setFloatingActionButton();

    }

    private void setupAlarmDataResource() {
        mAlarmRemoteHelper.findAll(new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                Collection<AlarmData> collection = (Collection<AlarmData>) data;
                reminderListViewAdapter.setParentList(Utils.convertCollectionToArrayList(collection));
                reminderListViewAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setFloatingActionButton() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlarmDialog = new AlarmDialog(UserSetReminderActivity.this, mCustomTimePickerDialogListener, 5, 20, true);

                mAlarmDialog.show();

                setDialogBtn(mAlarmDialog);
            }
        });
    }

    @TargetApi(16)
    protected void setDialogOkButton(Button okBtn) {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker mTimePicker = mAlarmDialog.getTimePicker();
                String hour;
                String minute;
                if (mTimePicker.getCurrentHour() < 10) {
                    hour = "0" + String.valueOf(mTimePicker.getCurrentHour());
                } else {
                    hour = String.valueOf(mTimePicker.getCurrentHour());
                }

                if (mTimePicker.getCurrentMinute() < 10) {
                    minute = "0" + String.valueOf(mTimePicker.getCurrentMinute());
                } else {
                    minute = String.valueOf(mTimePicker.getCurrentMinute());
                }
                AlarmData alarmData = new AlarmData(hour, minute, false, null);
                addAlarm(alarmData);
                mAlarmDialog.dismiss();
            }
        });
    }

    public void addAlarm(AlarmData alarmData) {
        mAlarmRemoteHelper.insert(alarmData);
    }

    protected void setDialogCancelBtn(Button cancelBtn) {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlarmDialog.dismiss();
            }
        });
    }

    protected void setDialogBtn(AlarmDialog alarmDialog) {
        setDialogOkButton(alarmDialog.getOkBtn());
        setDialogCancelBtn(alarmDialog.getCancelButton());
    }

}
