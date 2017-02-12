package com.siehuai.smartdrugbox.data;


public class AlarmData {

    private MyTime myTime;
    private int status;
    private long alarmID;

    public AlarmData(MyTime myTime, int status, long alarmID) {
        this.myTime = myTime;
        this.status = status;
        this.alarmID = alarmID;
    }

    public MyTime getMyTime() {
        return myTime;
    }

    public void setMyTime(MyTime myTime) {
        this.myTime = myTime;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(long alarmID) {
        this.alarmID = alarmID;
    }
}
