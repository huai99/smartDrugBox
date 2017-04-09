package com.siehuai.smartdrugbox.data.User;


public class AlarmData {

    private int hour;
    private int minute;
    private int status;
    private long alarmID;

    public AlarmData(int hour,
                     int minute,
                     int status,
                     long alarmID) {
        this.status = status;
        this.alarmID = alarmID;
        this.hour = hour;
        this.minute = minute;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public long getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(long alarmID) {
        this.alarmID = alarmID;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
