package com.siehuai.smartdrugbox.User.view.OrderMedicine;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.HTTPHelper.IResponseReturnListener;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.OrderMedicine.CheckMedicineHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.databinding.ActivityOrderMedicineBinding;

import java.util.ArrayList;

public class OrderMedicineActivity extends AppCompatActivity {

    ActivityOrderMedicineBinding mBinding;
    private String name;
    private String drugStore;
    private String frequency;
    private String price;
    private String description;
    private String moreInfo;
    private AlertDialogService mAlertDialogService;
    private Bitmap mBitmap;
    MedicineDetails mMedicineDetails;
    RequestQueue queue;
    CheckMedicineHelper mCheckMedicineHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_medicine);
        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(this);
        queue = Volley.newRequestQueue(this);
        mCheckMedicineHelper = new CheckMedicineHelper(queue);
        Intent i = getIntent();
        CompartmentDetails compartmentDetails = i.getParcelableExtra("CompartmentDetails");
        mMedicineDetails = compartmentDetails.getMedicineDetails();
        if (mMedicineDetails != null) {
            initData();
            initView();
            promptAlertDialog();
        } else {
            promptErrorDialog();
        }
    }


    private void initData() {
        name = mMedicineDetails.getMedicineName();
        drugStore = mMedicineDetails.getDrugstore();
        frequency = mMedicineDetails.getFrequencyOfTaking();
        price = String.valueOf(mMedicineDetails.getPrice());
        description = mMedicineDetails.getDescription();
        moreInfo = mMedicineDetails.getMedicineMoreInfo();
        mBitmap = Utils.Base64toBitMap(mMedicineDetails.getMedicineImg());
    }


    private void initView() {
        mBinding.editTextName.setText(name);
        mBinding.editTextDrugStore.setText(drugStore);
        mBinding.editTextFrequency.setText(frequency + " times/day");
        mBinding.editTextPrice.setText("$" + price);
        mBinding.editTextDescription.setText(description);
        mBinding.editTextMoreInfo.setText(moreInfo);
        mBinding.imageViewMedicine.setImageBitmap(mBitmap);
    }

    private void promptAlertDialog() {
        mAlertDialogService.provideCustomDialog(
                "This medicine has run out",
                "Order online from the drugstore nearby",
                "Ok",
                "Cancel",
                R.drawable.high_priority,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        promptOptionsDialog();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
    }

    private void promptErrorDialog() {
        mAlertDialogService.provideDefaultErrorDialog("Message Details should not be empty, please contact 8285 to find out more info ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private void promptOptionsDialog() {
        mAlertDialogService.provideCustomDialog(
                "Options",
                "Choose your preferred way of buying medicine",
                "System Manage",
                "Self Manage",
                R.drawable.high_priority,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        systemManageOrderMedicine();
                        dialog.cancel();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selfManageOrderMedicine();
                        dialog.cancel();
                    }
                }
        );
    }

    private void systemManageOrderMedicine() {
        MedicineOrderRemoteHelper remoteHelper = MedicineOrderRemoteHelper.getInstance();
        String id = remoteHelper.generateNewId();
        MedicineOrder medicineOrder = new MedicineOrder(id, "Sie Huai", "", "8284", mMedicineDetails, true, null);
        remoteHelper.insert(medicineOrder);
    }

    private void selfManageOrderMedicine() {
        mCheckMedicineHelper.sendRequestToViewPharmacyMenu(mMedicineDetails.getMedicineName(),
                new IResponseReturnListener() {
                    @Override
                    public void onResponseComplete(Object response) {
                        ArrayList<MedicineDetails> medicineDetailList = (ArrayList<MedicineDetails>) response;
                    }
                });
    }


}
