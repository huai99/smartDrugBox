package com.siehuai.smartdrugbox.data;

/**
 * Created by Asus on 2/9/2017.
 */

public class MyTime {
    private int hour;
    private int minute;

    public MyTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
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
