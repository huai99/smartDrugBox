package com.siehuai.smartdrugbox.Pharmacy.controller;

import com.google.firebase.messaging.FirebaseMessaging;

public class SubscribeToEventHelper {

    public static void subscribeToTopic(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
        return;
    }
}
