package com.siehuai.smartdrugbox.controller;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.data.AlarmData;
import com.siehuai.smartdrugbox.data.MyTime;

import java.util.ArrayList;
import java.util.Calendar;

public class ReminderListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<AlarmData> mParentList;
    private ExpandableListView mExpandableListView;
    private int lastExpandedGroupPosition;
    private Context mContext;
    private TextView mTextClock;
    private Switch mSwitch;
    private Calendar mCalendar;
    private Intent mIntent;
    private AlarmManager mAlarmManager;

    public ReminderListViewAdapter(Context context, ExpandableListView expandableListView, ArrayList<AlarmData> parentList) {
        mContext = context;
        mExpandableListView = expandableListView;
        mParentList = parentList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return mParentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParentList.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.design_parent_list_view_reminder, parent, false);
            mIntent = new Intent(this.mContext, AlarmReceiver.class);
            mAlarmManager = (AlarmManager) this.mContext.getSystemService(Context.ALARM_SERVICE);

        }
        convertView.setTag(this.getGroup(groupPosition).toString());

        mTextClock = (TextView) convertView.findViewById(R.id.textClock_parent_view);

        mSwitch = (Switch) convertView.findViewById(R.id.switch_parent_view);

        mCalendar = Calendar.getInstance();

        //TODO: Set tag using alarm ID
        mSwitch.setTag(groupPosition);

        switchToggleAction(mSwitch, groupPosition);

        setTextClock(groupPosition);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.design_child_list_view_reminder, parent, false);
        }
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

        if (groupPosition != lastExpandedGroupPosition) {
            mExpandableListView.collapseGroup(lastExpandedGroupPosition);
        }
        super.onGroupExpanded(groupPosition);

        lastExpandedGroupPosition = groupPosition;
    }

    @TargetApi(17)
    public void switchToggleAction(final Switch aSwitch, final int position) {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setAlarmOn(mCalendar, position);
                } else {
                    turnOffAlarm(position);
                }
            }
        });
    }

    public void setTextClock(int position) {

        MyTime aMyTime = mParentList.get(position).getMyTime();
        String hour_string = changeTimeToString(aMyTime.getHour());
        String minute_string = changeTimeToString(aMyTime.getMinute());
        mTextClock.setText(hour_string + ":" + minute_string);

    }

    @TargetApi(23)
    protected void setAlarmOn(final Calendar calendar, int position) {

        MyTime aMyTime = mParentList.get(position).getMyTime();

        int hour = aMyTime.getHour();
        int minute = aMyTime.getMinute();

        //setting the alarm time to the timepicker time
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        //Pass in the state of the request, yes for activate alarm
        mIntent.putExtra("extra", "yes");
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(mContext, position, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
    }

    private void turnOffAlarm(int position) {

        mIntent.putExtra("extra", "no");
        mContext.sendBroadcast(mIntent);

        PendingIntent mPendingIntent = PendingIntent.getBroadcast(mContext, position, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (mPendingIntent == null) {
            Log.d("UserReminder", "This pendingIntent is null");
        }

        mAlarmManager.cancel(mPendingIntent);
    }

    private String changeTimeToString(int time) {
        if (time < 10) {
            return "0" + String.valueOf(time);
        } else
            return String.valueOf(time);
    }

}
