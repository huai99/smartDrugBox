package com.siehuai.smartdrugbox.Generic.controller.MessageAction;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;

import java.util.Map;

public abstract class AbstractMessageAction implements IMessageAction {

    private String messageBody;
    private String messageTitle;
    protected Context mContext;
    private long mSentTime;
    private String sender;
    private String priority;
    private Map<String, String> data;
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

    public long getSentTime() {
        return mSentTime;
    }

    public void setSentTime(long sentTime) {
        mSentTime = sentTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    protected void createNotification(Intent intent,int notificationIcon) {
        PendingIntent mPendingIntent = PendingIntent.getActivities(mContext, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        mNotificationService = new NotificationService(mContext);
        String title = getMessageTitle();
        String content = getMessageBody();
        mNotificationService.createNotification(title, content, mPendingIntent, true, notificationIcon);
        mNotificationService.dispatchNotification();
    }

    public abstract void execute(Map<String, String> data);
}
