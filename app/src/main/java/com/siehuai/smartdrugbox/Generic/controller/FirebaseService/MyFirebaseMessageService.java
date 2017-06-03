package com.siehuai.smartdrugbox.Generic.controller.FirebaseService;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.IMessageAction;
import com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl.P_MessageActionFactory;
import com.siehuai.smartdrugbox.User.controller.MessageActionImpl.UserMessageActionFactory;

public class MyFirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String userGroup = remoteMessage.getData().get("userGroup");
        IMessageAction messageAction;
        Log.d("MyFirebaseMessage", userGroup);
        if (userGroup.equals("User")) {
            messageAction = UserMessageActionFactory.messageActionDispatch(remoteMessage, this);
        } else {
            messageAction = P_MessageActionFactory.messageActionDispatch(remoteMessage, this);
        }
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
