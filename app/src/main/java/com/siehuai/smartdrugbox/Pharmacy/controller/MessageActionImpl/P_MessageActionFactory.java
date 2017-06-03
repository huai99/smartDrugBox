package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.IMessageAction;

public class P_MessageActionFactory {

    public static IMessageAction messageActionDispatch(RemoteMessage message, Context context) {
        String action = message.getData().get("action");
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        Log.d("P_MessageActionFactory", action);
        switch (action) {
            case "NewMedicineOrderAction":
                NewMedicineOrderAction newMedicineOrderAction = new NewMedicineOrderAction(context);
                newMedicineOrderAction.setMessageTitle(title);
                newMedicineOrderAction.setMessageBody(body);
                return newMedicineOrderAction;
            default:
                return null;
        }
    }
}
