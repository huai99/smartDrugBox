package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder.P_MedicineOrder;

import java.util.Map;

public class NewMedicineOrderAction extends AbstractMessageAction {

    public NewMedicineOrderAction(Context context) {
        super(context);
    }

    @Override
    public void execute(Map<String, String> data) {
        Intent intent = new Intent(mContext, P_MedicineOrder.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        createNotification(intent);
        Log.d("Firebase Message", getMessageBody());
    }
}
