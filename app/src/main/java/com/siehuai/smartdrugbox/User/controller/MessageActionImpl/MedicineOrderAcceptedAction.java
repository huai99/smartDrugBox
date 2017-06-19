package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.content.Context;
import android.content.Intent;

import com.siehuai.smartdrugbox.Generic.view.MainActivity;
import com.siehuai.smartdrugbox.R;

import java.util.Map;


public class MedicineOrderAcceptedAction extends UserAbstractMessageAction {

    public MedicineOrderAcceptedAction(Context context) {
        super(context);
    }

    @Override
    public void execute(Map<String, String> data) {
        Intent intent = new Intent(mContext, MainActivity.class);
        createNotification(intent, R.drawable.ok_hand_icon);
    }




}
