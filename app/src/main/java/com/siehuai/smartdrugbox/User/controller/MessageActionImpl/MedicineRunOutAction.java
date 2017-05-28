package com.siehuai.smartdrugbox.User.controller.MessageActionImpl;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.controller.MessageAction.AbstractMessageAction;
import com.siehuai.smartdrugbox.Generic.controller.Service.NotificationService;
import com.siehuai.smartdrugbox.Generic.view.MainActivity;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;

import java.util.Map;

import javax.inject.Inject;

public class MedicineRunOutAction extends AbstractMessageAction {

    Context mContext;

    @Inject
    NotificationService mNotificationService;

    public MedicineRunOutAction(Context context) {
        mContext = context;
    }

    @Override
    public void execute(Map<String, String> data) {
        String id = data.get("id");
        String medicineBoxId = data.get("medicineBoxId");
        MedicineBoxCompartmentRemoteHelper remoteHelper = new MedicineBoxCompartmentRemoteHelper();
        remoteHelper.find(medicineBoxId, new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                MedicineBoxCompartment medicineBoxCompartment = (MedicineBoxCompartment) data;
                Log.d("Firebase Message", medicineBoxCompartment.toString());
            }
        });
        Intent intent = new Intent(mContext,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        createNotification(intent);
        Log.d("Firebase Message", getMessageBody());
        mNotificationService.dispatchNotification();
    }

    private void createNotification(Intent intent) {
        PendingIntent mPendingIntent = PendingIntent.getActivities(mContext, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        mNotificationService = new NotificationService(mContext);
        String title = getMessageTitle();
        String content = getMessageBody();
        mNotificationService.createNotification(title, content, mPendingIntent, true, R.drawable.medicine_simple_icon);
    }
}
