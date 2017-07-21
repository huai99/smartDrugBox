package com.siehuai.smartdrugbox.User.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class AlarmData implements IDbData, Parcelable {

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

    protected AlarmData(Parcel in) {
        id = in.readString();
        hour = in.readString();
        minute = in.readString();
        status = in.readInt() != 0;
    }

    public static final Creator<AlarmData> CREATOR = new Creator<AlarmData>() {
        @Override
        public AlarmData createFromParcel(Parcel in) {
            return new AlarmData(in);
        }

        @Override
        public AlarmData[] newArray(int size) {
            return new AlarmData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(hour);
        dest.writeString(minute);
        dest.writeInt(status ? 1 : 0);
    }

}
