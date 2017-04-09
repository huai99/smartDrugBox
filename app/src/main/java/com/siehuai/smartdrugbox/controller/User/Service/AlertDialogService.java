package com.siehuai.smartdrugbox.controller.User.Service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.siehuai.smartdrugbox.R;

public class AlertDialogService {

    private AlertDialog mAlertDialog;
    private Context mContext;
    private AlertDialog.Builder mAlertDialogBuilder;

    public AlertDialogService(Context context) {
        mContext = context;
    }

    public AlertDialog provideDefaultErrorDialog(String message,
                                                 DialogInterface.OnClickListener yesButtonClick) {
        if (yesButtonClick == null) {
            yesButtonClick = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }
        return new AlertDialog.Builder(mContext)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, yesButtonClick)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public AlertDialog provideDefaultPriorityDialog(String message,
                                                    DialogInterface.OnClickListener yesButtonClick,
                                                    DialogInterface.OnClickListener noButtonClick) {
        if (yesButtonClick == null) {
            yesButtonClick = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }

        if (noButtonClick == null) {
            noButtonClick = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }
        return new AlertDialog.Builder(mContext)
                .setTitle("High Priority")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, yesButtonClick)
                .setNegativeButton(android.R.string.no, noButtonClick)
                .setIcon(R.drawable.high_priority)
                .show();
    }

    public AlertDialog provideDefaultOkDialog(String message,
                                              DialogInterface.OnClickListener yesButtonClick,
                                              DialogInterface.OnClickListener noButtonClick) {
        return new AlertDialog.Builder(mContext)
                .setTitle("Ok")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, yesButtonClick)
                .setNegativeButton(android.R.string.no, noButtonClick)
                .setIcon(R.drawable.successful)
                .show();
    }
}
