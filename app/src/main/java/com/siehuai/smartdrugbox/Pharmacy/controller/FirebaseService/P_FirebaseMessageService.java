package com.siehuai.smartdrugbox.Pharmacy.controller.FirebaseService;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.IMessageAction;
import com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl.P_MessageActionFactory;

public class P_FirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String action = remoteMessage.getData().get("action");
        IMessageAction messageAction = P_MessageActionFactory.messageActionDispatch(remoteMessage,this);
        messageAction.execute(remoteMessage.getData());
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }


}
