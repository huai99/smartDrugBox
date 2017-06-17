package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.content.Context;

import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.IMessageAction;

public class UserMessageActionFactory {

    public static IMessageAction messageActionDispatch(RemoteMessage message, Context context) {
        String action = message.getData().get("action");
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        String sentTime = Utils.convertLongTimeToStringFormat(message.getSentTime(), "HH:mm:ss");
        String sender = message.getData().get("sender");
        String priority = message.getData().get("priority");
        AbstractMessageAction abstractMessageAction = null;
        switch (action) {
            case "MedicineRunOutAction":
                abstractMessageAction = new MedicineRunOutAction(context);
                break;
            case "MedicineOrderAcceptedAction":
                abstractMessageAction = new MedicineOrderAcceptedAction(context);
                break;
        }
        abstractMessageAction.setMessageTitle(title);
        abstractMessageAction.setMessageBody(body);
        abstractMessageAction.setSentTime(sentTime);
        abstractMessageAction.setPriority(priority);
        abstractMessageAction.setSender(sender);
        abstractMessageAction.setData(message.getData());
        return abstractMessageAction;
    }
}
