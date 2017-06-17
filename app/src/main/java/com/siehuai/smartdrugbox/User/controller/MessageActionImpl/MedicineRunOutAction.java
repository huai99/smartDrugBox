package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;
import com.siehuai.smartdrugbox.User.view.OrderMedicine.OrderMedicineActivity;

import java.util.Map;

public class MedicineRunOutAction extends UserAbstractMessageAction {

    public MedicineRunOutAction(Context context) {
        super(context);
    }

    @Override
    public void execute(Map<String, String> data) {
        final String key = "Compartment " + data.get("id");
        String medicineBoxId = data.get("medicineBoxId");
        MedicineBoxCompartmentRemoteHelper remoteHelper = new MedicineBoxCompartmentRemoteHelper();
        remoteHelper.find(medicineBoxId, new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                MedicineBoxCompartment medicineBoxCompartment = (MedicineBoxCompartment) data;
                Log.d("Firebase Message", medicineBoxCompartment.toString());
                Intent intent = new Intent(mContext, OrderMedicineActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                CompartmentDetails compartmentDetails = medicineBoxCompartment.getCompartmentDetailsMap().get(key);
                intent.putExtra("CompartmentDetails", compartmentDetails);
                createNotification(intent);
                Log.d("Firebase Message", getMessageBody());
            }
        });
    }

}
