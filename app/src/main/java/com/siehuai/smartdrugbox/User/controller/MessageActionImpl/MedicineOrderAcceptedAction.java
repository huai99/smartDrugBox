package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.content.Context;
import android.content.Intent;

import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.view.MainActivity;

import java.util.Map;


public class MedicineOrderAcceptedAction extends AbstractMessageAction {

    Context mContext;

    public MedicineOrderAcceptedAction(Context context) {
        super(context);
    }

    @Override
    public void execute(Map<String, String> data) {
        Intent intent = new Intent(mContext, MainActivity.class);
        createNotification(intent);
    }




}
