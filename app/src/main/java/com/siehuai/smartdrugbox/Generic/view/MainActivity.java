package com.siehuai.smartdrugbox.Generic.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioGroup;

import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.Generic.data.Role;
import com.siehuai.smartdrugbox.Pharmacy.controller.SubscribeToEventHelper;
import com.siehuai.smartdrugbox.Pharmacy.view.P_MainActivity;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.view.U_MainActivity;
import com.siehuai.smartdrugbox.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding mBinding;
    AlertDialogService mAlertDialogService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(this);
        SubscribeToEventHelper.unSubscribeTopic("medicineOrder");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpRadioBtn();
        setUpLoginBtn();
    }

    private void setUpEditText() {
        if (mBinding.radioBtnUser.isChecked()) {
            mBinding.editTextUserName.setText(Role.DEFAULT_PATIENT_USERNAME);
        } else {
            mBinding.editTextUserName.setText(Role.DEFAULT_PHARMACY_USERNAME);
        }
    }

    private void setUpRadioBtn() {
        mBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                setUpEditText();
            }
        });
    }

    private void signInAsUser() {
        if (mBinding.editTextUserName.getText() == null) {
            promptNoUserNameErrorDialog();
        } else {
            String userName = String.valueOf(mBinding.editTextUserName.getText());
            Role.PATIENT_USERNAME = userName;
            Role.CURRENT_ROLE = Role.USER;
            Intent intent = new Intent(MainActivity.this, U_MainActivity.class);
            startActivity(intent);
        }
    }

    private void signInAsPharmacy() {
        if (mBinding.editTextUserName.getText() == null) {
            promptNoUserNameErrorDialog();
        } else {
            String userName = String.valueOf(mBinding.editTextUserName.getText());
            Role.PHARMACY_USERNAME = userName;
            Role.CURRENT_ROLE = Role.PHARMACY;
            Intent intent = new Intent(MainActivity.this, P_MainActivity.class);
            startActivity(intent);
        }
    }

    private void setUpLoginBtn() {
        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.radioBtnUser.isChecked()) {
                    signInAsUser();
                } else {
                    signInAsPharmacy();
                }
            }
        });
    }

    private void promptNoUserNameErrorDialog() {
        mAlertDialogService.provideDefaultErrorDialog("Please key in your user name", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

//    private void setUserBtnOnClick() {
//        mBinding.btnLoginUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, U_MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//    private void setPharmacyBtnOnClick() {
//        mBinding.btnLoginPharmacy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, P_MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

}
