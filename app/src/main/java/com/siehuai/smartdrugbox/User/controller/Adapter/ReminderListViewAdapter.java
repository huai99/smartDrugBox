package com.siehuai.smartdrugbox.User.controller.Adapter;

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

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnCompleteListener;
import com.siehuai.smartdrugbox.Generic.controller.Service.SetAlarmService;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.U_AlarmRemoteHelper;
import com.siehuai.smartdrugbox.User.data.AlarmData;

import java.util.ArrayList;


//TODO:Build an abstraction over this
public class ReminderListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<AlarmData> mParentList = new ArrayList<>();
    private ExpandableListView mExpandableListView;
    private int lastExpandedGroupPosition;
    private Context mContext;
    private TextView mTextClock;
    private Switch mSwitch;
    private U_AlarmRemoteHelper mAlarmRemoteHelper;
    private SetAlarmService mSetAlarmService;

    public ReminderListViewAdapter(Context context,
                                   ExpandableListView expandableListView) {
        mContext = context;
        mExpandableListView = expandableListView;
        mSetAlarmService = new SetAlarmService(context);
    }

    public void setAlarmRemoteHelper(U_AlarmRemoteHelper alarmRemoteHelper) {
        mAlarmRemoteHelper = alarmRemoteHelper;
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

        AlarmData alarmData = mParentList.get(groupPosition);

        mTextClock = (TextView) convertView.findViewById(R.id.textClock_parent_view);

        mSwitch = (Switch) convertView.findViewById(R.id.switch_parent_view);

        String mAlarmId = alarmData.getId();

        boolean status = alarmData.isStatus();

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

        AlarmData alarmData = mParentList.get(groupPosition);

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

    @TargetApi(19)
    private void switchToggleAction(final Switch aSwitch,
                                    final int position) {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlarmData alarmData = mParentList.get(position);
                if (isChecked) {
                    setAlarmOn(alarmData);
//                    Set alarm on in db
                    alarmData.setStatus(true);
                } else {
                    cancelAlarm(alarmData);
                    //Set alarm off in db
                    alarmData.setStatus(false);
                }
                mAlarmRemoteHelper.update(alarmData);
            }
        });
    }

    private void setTextClock(AlarmData alarmData) {

        if (Utils.safeParseInteger(alarmData.getHour()) < 12) {
            String hour = alarmData.getHour();
            String minute = alarmData.getMinute();
            mTextClock.setText(hour + ":" + minute + " a.m.");

        } else {
            int hour = Utils.safeParseInteger(alarmData.getHour()) - 12;
            String hour_string = String.valueOf(hour);
            String minute_string = alarmData.getMinute();
            mTextClock.setText(hour_string + ":" + minute_string + " p.m.");
        }
    }

    //TODO: The alarm will go off immediately if the time is past already
    @TargetApi(19)
    private void setAlarmOn(AlarmData alarmData) {
        mSetAlarmService.setAlarmOn(alarmData);
    }

    private void cancelAlarm(AlarmData alarmData) {
        mSetAlarmService.cancelAlarm(alarmData);
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
                mAlarmRemoteHelper.delete(alarmData, new IDbOnCompleteListener() {
                    @Override
                    public void onComplete(Object error) {
                        if (error == null) {
                            if (mSwitch.isChecked()) {
                                cancelAlarm(alarmData);
                            }
                        }
                    }
                });
            }
        });
    }

    private void setSwitchInitialStatus(Switch aSwitch, boolean status) {
        aSwitch.setChecked(status);
    }


    public void setParentList(ArrayList<AlarmData> parentList) {
        if (parentList != null) {
            mParentList = parentList;
        }
    }

}
