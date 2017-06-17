package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.IMessageAction;

public class P_MessageActionFactory {

    public static IMessageAction messageActionDispatch(RemoteMessage message, Context context) {
        String action = message.getData().get("action");
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        String sentTime = Utils.convertLongTimeToStringFormat(message.getSentTime(), "HH:mm:ss");
        Log.d("P_MessageActionFactory", action);
        switch (action) {
            case "NewMedicineOrderAction":
                NewMedicineOrderAction newMedicineOrderAction = new NewMedicineOrderAction(context);
                newMedicineOrderAction.setMessageTitle(title);
                newMedicineOrderAction.setMessageBody(body);
                newMedicineOrderAction.setSentTime(sentTime);
                return newMedicineOrderAction;
            case "NewSpecializedOrderAction":
                NewSpecializedOrderAction newSpecializedOrderAction = new NewSpecializedOrderAction(context);
                newSpecializedOrderAction.setMessageTitle(title);
                newSpecializedOrderAction.setMessageBody(body);
                newSpecializedOrderAction.setSentTime(sentTime);
                return newSpecializedOrderAction;
            default:
                return null;
        }
    }
}
