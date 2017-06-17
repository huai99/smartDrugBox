package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;

import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.IMessageAction;

public class P_MessageActionFactory {

    public static IMessageAction messageActionDispatch(RemoteMessage message, Context context) {
        String action = message.getData().get("action");
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        String sentTime = Utils.convertLongTimeToStringFormat(message.getSentTime(), "HH:mm:ss");
        AbstractMessageAction abstractMessageAction = null;
        switch (action) {
            case "NewMedicineOrderAction":
                abstractMessageAction = new NewMedicineOrderAction(context);
                break;
            case "NewSpecializedOrderAction":
                abstractMessageAction = new NewSpecializedOrderAction(context);
                break;
        }
        abstractMessageAction.setMessageTitle(title);
        abstractMessageAction.setMessageBody(body);
        abstractMessageAction.setSentTime(sentTime);
        return abstractMessageAction;
    }
}
