package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;

import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.data.Message;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MessageQueueRemoteHelper;


public abstract class P_AbstractMessageAction extends AbstractMessageAction {

    P_MessageQueueRemoteHelper mMessageQueueRemoteHelper;


    public P_AbstractMessageAction(Context context) {
        super(context);
    }

    @Override
    public void execute() {
        execute(getData());
        addToMessageQueue();
    }

    protected void addToMessageQueue() {
        mMessageQueueRemoteHelper = P_MessageQueueRemoteHelper.getInstance();
        String id = mMessageQueueRemoteHelper.generateNewId();
        Message message = new Message(id, getSentTime(), getMessageTitle(), getMessageBody(), false, getSender(), getPriority());
        mMessageQueueRemoteHelper.insert(message);
    }

}
