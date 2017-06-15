package com.siehuai.smartdrugbox.Pharmacy.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.DaggerP_RemoteHelperComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_RemoteHelperComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.SubscribeToEventHelper;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.ActivityPMainBinding;

import javax.inject.Inject;

public class P_MainActivity extends P_MainBaseActivity {

    ActivityPMainBinding mBinding;

    @Inject
    P_MedicineDetailsRemoteHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        P_RemoteHelperComponent remoteHelperComponent = DaggerP_RemoteHelperComponent.create();
        remoteHelperComponent.inject(this);
        SubscribeToEventHelper.subscribeToTopic("medicineOrder");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_main);
        setUpEditCatalogueBtnOnClick();
        setUpAddMedicineBtnOnClick();
        getAllRemoteData();
    }

    private void setUpEditCatalogueBtnOnClick() {
        mBinding.btnEditCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P_MainActivity.this, P_EditTabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpAddMedicineBtnOnClick() {
        mBinding.btnAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P_MainActivity.this, P_AddMedicineActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAllRemoteData() {
        mDbHelper.read();
    }


}
