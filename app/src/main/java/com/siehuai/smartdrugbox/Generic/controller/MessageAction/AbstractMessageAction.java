package com.siehuai.smartdrugbox.Generic.controller.MessageAction;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;
import com.siehuai.smartdrugbox.R;

public abstract class AbstractMessageAction implements IMessageAction {

    private String messageBody;
    private String messageTitle;
    protected Context mContext;
    private String mSentTime;
    private NotificationService mNotificationService;

    public AbstractMessageAction(Context context) {
        mContext = context;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getSentTime() {
        return mSentTime;
    }

    public void setSentTime(String sentTime) {
        mSentTime = sentTime;
    }

    protected void createNotification(Intent intent) {
        PendingIntent mPendingIntent = PendingIntent.getActivities(mContext, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        mNotificationService = new NotificationService(mContext);
        String title = getMessageTitle();
        String content = getMessageBody();
        mNotificationService.createNotification(title, content, mPendingIntent, true, R.drawable.ok_hand_icon);
        mNotificationService.dispatchNotification();
    }

}
