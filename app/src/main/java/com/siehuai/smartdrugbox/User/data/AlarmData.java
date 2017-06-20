package com.siehuai.smartdrugbox.User.data;


import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class AlarmData implements IDbData {

    private String hour;
    private String minute;
    private boolean status;
    private String id;

    public AlarmData() {
    }

    public AlarmData(String hour,
                     String minute,
                     boolean status,
                     String id) {
        this.status = status;
        this.id = id;
        this.hour = hour;
        this.minute = minute;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}
