package com.siehuai.smartdrugbox.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getExtras().getString("extra");
        Intent serviceIntent = new Intent(context,RingtoneService.class);
        serviceIntent.putExtra("extra",state);
        context.startService(serviceIntent);
    }

}
