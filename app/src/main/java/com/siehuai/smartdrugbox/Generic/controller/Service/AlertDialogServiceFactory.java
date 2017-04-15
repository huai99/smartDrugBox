package com.siehuai.smartdrugbox.Generic.controller.Service;

import android.content.Context;

public class AlertDialogServiceFactory {
    public static AlertDialogService createAlertDialogService(Context context){
        return new AlertDialogService(context);
    }
}
