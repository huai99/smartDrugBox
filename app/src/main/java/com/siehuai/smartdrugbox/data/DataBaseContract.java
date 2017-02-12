package com.siehuai.smartdrugbox.data;

import android.provider.BaseColumns;

public final class DataBaseContract {
    private DataBaseContract(){}

    public static class AlarmEntry implements BaseColumns{
        public static final String TABLE_NAME="Alarm_Entry";
        public static final String COLUMN_NAME_ID="Alarm_ID";
        public static final String COLUMN_NAME_A_HOUR="Alarm_Hour";
        public static final String COLUMN_NAME_A_MINUTE="Alarm_Minute";
        public static final String COLUMN_NAME_STATUS="Alarm_Status";
    }


}
