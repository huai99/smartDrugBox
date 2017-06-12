package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;
import com.siehuai.smartdrugbox.Generic.view.MainActivity;
import com.siehuai.smartdrugbox.R;

import java.util.Map;

import javax.inject.Inject;


public class MedicineOrderAcceptedAction extends AbstractMessageAction {

    Context mContext;

    @Inject
    NotificationService mNotificationService;

    public MedicineOrderAcceptedAction(Context context) {
        mContext = context;
    }

    @Override
    public void execute(Map<String, String> data) {

        Intent intent = new Intent(mContext, MainActivity.class);
        createNotification(intent);
    }

    private void createNotification(Intent intent) {
        PendingIntent mPendingIntent = PendingIntent.getActivities(mContext, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        mNotificationService = new NotificationService(mContext);
        String title = getMessageTitle();
        String content = getMessageBody();
        mNotificationService.createNotification(title, content, mPendingIntent, true, R.drawable.ok_hand_icon);
        mNotificationService.dispatchNotification();
    }
}
