package com.siehuai.smartdrugbox.Pharmacy.controller.MessageActionImpl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder.P_MedicineOrder;
import com.siehuai.smartdrugbox.R;

import java.util.Map;

public class NewMedicineOrderAction extends P_AbstractMessageAction {

    public NewMedicineOrderAction(Context context) {
        super(context);
    }

    @Override
    public void execute(Map<String, String> data) {
        Intent intent = new Intent(mContext, P_MedicineOrder.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        createNotification(intent, R.drawable.medicine_box_icon);
        Log.d("Firebase Message", getMessageBody());
    }
}
