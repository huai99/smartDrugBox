package com.siehuai.smartdrugbox.controller.User;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.siehuai.smartdrugbox.controller.User.Service.AfterAlarmSetService;


public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getExtras().getString("extra");
        int alarmId = intent.getExtras().getInt("alarmId");

        Intent serviceIntent = new Intent(context, AfterAlarmSetService.class);
        serviceIntent.putExtra("extra", state);
        serviceIntent.putExtra("alarmId", alarmId);

        context.startService(serviceIntent);
    }

}
