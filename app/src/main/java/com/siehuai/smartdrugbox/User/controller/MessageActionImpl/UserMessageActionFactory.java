package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.content.Context;

import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.IMessageAction;

public class UserMessageActionFactory {

    public static IMessageAction messageActionDispatch(RemoteMessage message, Context context) {
        String action = message.getData().get("action");
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        switch (action) {
            case "MedicineRunOutAction":
                MedicineRunOutAction medicineRunOutAction = new MedicineRunOutAction(context);
                medicineRunOutAction.setMessageTitle(title);
                medicineRunOutAction.setMessageBody(body);
                return medicineRunOutAction;
            default:
                return null;
        }
    }
}
