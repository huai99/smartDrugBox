package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;
import com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder.P_ViewMedicineOrder;
import com.siehuai.smartdrugbox.R;

import java.util.Map;

import javax.inject.Inject;

public class NewMedicineOrderAction extends AbstractMessageAction {

    Context mContext;

    @Inject
    NotificationService mNotificationService;

    public NewMedicineOrderAction(Context context) {
        mContext = context;
    }

    @Override
    public void execute(Map<String, String> data) {
        Intent intent = new Intent(mContext, P_ViewMedicineOrder.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        createNotification(intent);
        Log.d("Firebase Message", getMessageBody());
        mNotificationService.dispatchNotification();
    }

    private void createNotification(Intent intent) {
        PendingIntent mPendingIntent = PendingIntent.getActivities(mContext, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        mNotificationService = new NotificationService(mContext);
        String title = getMessageTitle();
        String content = getMessageBody();
        mNotificationService.createNotification(title, content, mPendingIntent, true, R.drawable.purchase_order);
        mNotificationService.dispatchNotification();
    }
}
