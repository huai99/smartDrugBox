package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.siehuai.smartdrugbox.Pharmacy.view.OrderQueue.OrderQueueActivity;

import java.util.Map;

public class NewSpecializedOrderAction extends P_AbstractMessageAction {


    public NewSpecializedOrderAction(Context context) {
        super(context);
    }

    @Override
    public void execute(Map<String, String> data) {
        Intent intent = new Intent(mContext, OrderQueueActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        createNotification(intent);
        Log.d("Firebase Message", getMessageBody());
    }

}
