package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.content.Context;

import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.data.Message;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.UserMessageQueueRemoteHelper;


public abstract class UserAbstractMessageAction extends AbstractMessageAction {

    UserMessageQueueRemoteHelper mMessageQueueRemoteHelper;


    public UserAbstractMessageAction(Context context) {
        super(context);
    }

    @Override
    public void execute() {
        execute(getData());
        addToMessageQueue();
    }

    protected void addToMessageQueue() {
        mMessageQueueRemoteHelper = UserMessageQueueRemoteHelper.getInstance();
        String id = mMessageQueueRemoteHelper.generateNewId();
        Message message = new Message(id, getSentTime(), getMessageTitle(), getMessageBody(), false, getSender(), getPriority());
        mMessageQueueRemoteHelper.insert(message);
    }
}
