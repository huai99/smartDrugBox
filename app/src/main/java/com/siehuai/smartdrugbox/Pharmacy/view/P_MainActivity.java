package com.siehuai.smartdrugbox.Pharmacy.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper.DaggerP_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper.P_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.SubscribeToEventHelper;
import com.siehuai.smartdrugbox.Pharmacy.view.MessageQueue.P_MessageQueueActivity;
import com.siehuai.smartdrugbox.Pharmacy.view.OrderQueue.OrderQueueActivity;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.ActivityPMainBinding;

import javax.inject.Inject;

public class P_MainActivity extends P_MainBaseActivity {

    ActivityPMainBinding mBinding;

    @Inject
    P_MedicineDetailsRemoteHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        P_DependencyInjectionComponent remoteHelperComponent = DaggerP_DependencyInjectionComponent.create();
        remoteHelperComponent.inject(this);
        SubscribeToEventHelper.subscribeToTopic("medicineOrder");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pharmacy_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.order_queue:
                goToOrderQueue();
                break;
            case R.id.message_queue:
                goToMessageQueue();
                break;
            default:
        }
        return true;
    }

    private void goToOrderQueue() {
        Intent intent = new Intent(this, OrderQueueActivity.class);
        startActivity(intent);
    }

    private void goToMessageQueue() {
        Intent intent = new Intent(this, P_MessageQueueActivity.class);
        startActivity(intent);
    }
}
