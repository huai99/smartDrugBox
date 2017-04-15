package com.siehuai.smartdrugbox.User.data;

import java.util.ArrayList;

public class AlarmDataHelper {

    private static ArrayList<AlarmData> mAlarmDataList = new ArrayList<AlarmData>() {
    };

    public static ArrayList<AlarmData> getAlarmDataList() {
        return mAlarmDataList;
    }

    public static void addAlarm(AlarmData alarmData) {
        mAlarmDataList.add(alarmData);
    }

    public static boolean removeAlarm(AlarmData alarmData) {
        return mAlarmDataList.remove(alarmData);
    }

    public static void setAlarmDataList(ArrayList<AlarmData> alarmDataList) {
        mAlarmDataList = alarmDataList;
    }

    public static void modifyAlarmData(AlarmData newAlarmData) {
        for (AlarmData mAlarmData : mAlarmDataList) {
            if (newAlarmData.getAlarmID() == mAlarmData.getAlarmID()) {
                mAlarmData = newAlarmData;
            }
        }
    }

}
