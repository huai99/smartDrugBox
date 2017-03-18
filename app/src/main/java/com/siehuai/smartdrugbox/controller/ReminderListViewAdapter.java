package com.siehuai.smartdrugbox.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.data.AlarmData;
import com.siehuai.smartdrugbox.data.AlarmDataService;

import java.util.ArrayList;

public class ReminderListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<AlarmData> mParentList;
    private ExpandableListView mExpandableListView;
    private int lastExpandedGroupPosition;
    private Context mContext;
    private TextView mTextClock;
    private Switch mSwitch;
    private PostsDatabaseHelper postsDbHelper;
    private AlarmService mAlarmService;

    public ReminderListViewAdapter(Context context,
                                   ExpandableListView expandableListView,
                                   ArrayList<AlarmData> parentList) {
        mContext = context;
        mExpandableListView = expandableListView;
        mParentList = parentList;
        postsDbHelper = PostsDatabaseHelper.getInstance(context);
        mAlarmService = new AlarmService(context);
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
    public View getGroupView(int groupPosition,
                             boolean isExpanded,
                             View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.design_parent_list_view_reminder, parent, false);
        }
        convertView.setTag(this.getGroup(groupPosition).toString());

        AlarmData alarmData = AlarmDataService.getAlarmDataList().get(groupPosition);

        mTextClock = (TextView) convertView.findViewById(R.id.textClock_parent_view);

        mSwitch = (Switch) convertView.findViewById(R.id.switch_parent_view);

        int mAlarmId = (int) alarmData.getAlarmID();

        int status = alarmData.isStatus();

        setSwitchInitialStatus(mSwitch, status);

        mSwitch.setTag(mAlarmId);

        switchToggleAction(mSwitch, groupPosition);

        setTextClock(alarmData);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition,
                             int childPosition,
                             boolean isLastChild,
                             View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.design_child_list_view_reminder, parent, false);
        }

        AlarmData alarmData = AlarmDataService.getAlarmDataList().get(groupPosition);

        Button deleteBtn = (Button) convertView.findViewById(R.id.btn_delete);

        setDeleteBtn(deleteBtn, alarmData);
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
    public void switchToggleAction(final Switch aSwitch,
                                   final int position) {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlarmData alarmData = AlarmDataService.getAlarmDataList().get(position);
                if (isChecked) {
                    setAlarmOn(alarmData);
//                    Set alarm on in db
                    alarmData.setStatus(1);
                } else {
                    turnOffAlarm(alarmData);
                    //Set alarm off in db
                    alarmData.setStatus(0);
                }
                addOrUpdateAlarmData(alarmData);
            }
        });
    }

    public void setTextClock(AlarmData alarmData) {

        String hour_string = changeTimeToString(alarmData.getHour());
        String minute_string = changeTimeToString(alarmData.getMinute());
        mTextClock.setText(hour_string + ":" + minute_string);

    }

    //TODO: The alarm will go off immediately if the time is past already
    @TargetApi(23)
    protected void setAlarmOn(AlarmData alarmData) {
        mAlarmService.setAlarmOn(alarmData);
    }

    private void turnOffAlarm(AlarmData alarmData) {
        mAlarmService.turnOffAlarm(alarmData);
    }


    private String changeTimeToString(int time) {
        if (time < 10) {
            return "0" + String.valueOf(time);
        } else
            return String.valueOf(time);
    }

    private void setDeleteBtn(Button deleteBtn, final AlarmData alarmData) {
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int alarmId = (int) alarmData.getAlarmID();
                if (postsDbHelper.deleteAlarmFromDb(alarmId)) {
                    deleteAlarmLocal(alarmData);
                    Toast.makeText(mContext, "Delete Successfully", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    if (mSwitch.isChecked()) {
                        turnOffAlarm(alarmData);
                    }
                } else {
                    Toast.makeText(mContext, "Delete Fail: " + String.valueOf(alarmId), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean deleteAlarmLocal(AlarmData alarmData) {
        return AlarmDataService.removeAlarm(alarmData);
    }

    private void setSwitchInitialStatus(Switch aSwitch, int status) {
        boolean switchStatus = status != 0;

        aSwitch.setChecked(switchStatus);
    }

    private void addOrUpdateAlarmData(AlarmData mAlarmData) {
        boolean result;
        int resultNum = postsDbHelper.addOrUpdateAlarmFrmDb(mAlarmData);
        result = (resultNum > 0);
        postsDbHelper.addOrUpdateAlarmLocal(mAlarmData, result);
        notifyDataSetChanged();
    }

}
