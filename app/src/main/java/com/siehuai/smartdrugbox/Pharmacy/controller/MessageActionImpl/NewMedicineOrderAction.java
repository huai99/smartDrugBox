package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;

import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;

import java.util.Map;

import javax.inject.Inject;

public class NewMedicineOrderAction extends AbstractMessageAction {

    Context mContext;

    @Inject
    NotificationService mNotificationService;

    public NewMedicineOrderAction(Context context) {
        mContext = context;
    }

    @Override
    public void execute(Map<String, String> data) {

    }
}
