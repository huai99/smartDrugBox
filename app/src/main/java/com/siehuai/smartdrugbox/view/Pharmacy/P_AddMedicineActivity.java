package com.siehuai.smartdrugbox.view.Pharmacy;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.RemoteDbFactory;
import com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.controller.User.Service.AlertDialogService;
import com.siehuai.smartdrugbox.data.Pharmacy.P_MedicineDetails;
import com.siehuai.smartdrugbox.databinding.ActivityPAddMedicineBinding;

import java.util.ArrayList;

import static com.siehuai.smartdrugbox.common.Utils.safeParseDouble;
import static com.siehuai.smartdrugbox.common.Utils.safeParseInteger;

public class P_AddMedicineActivity extends AppCompatActivity {

    private ActivityPAddMedicineBinding mBinding;
    private String medicineName;
    private double price;
    private String description;
    private int frequencyOfTaking;
    private String medicineMoreInfo;
    private int medicineImage;
    private RemoteDbHelper mRemoteDbHelper;
    private AlertDialogService mAlertDialogService;
    private ArrayList<String> errorMsgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_add_medicine);
        mRemoteDbHelper = RemoteDbFactory.createRemoteDbHelper(RemoteDbFactory.RemoteDataType.PharmacyMedicineDetails);
        mAlertDialogService = new AlertDialogService(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_add_medicine);
        setConfirmBtnOnClick();
    }

    private void getAllInputDetails() {
        medicineName = mBinding.editTextName.getText().toString();
        price = safeParseDouble(mBinding.editTextPrice.getText().toString());
        description = mBinding.editTextDescription.getText().toString();
        frequencyOfTaking = safeParseInteger(mBinding.editTextFrequencyOfTaking.getText().toString());
        medicineMoreInfo = mBinding.editTextMoreInfo.getText().toString();
        medicineImage = 0;
    }

    private void insertMedicineRemote() {
        P_MedicineDetails p_medicineDetails = new P_MedicineDetails(
                medicineName, price, description, frequencyOfTaking, medicineMoreInfo, medicineImage);
        mRemoteDbHelper.attachOnCompleteListener(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    mAlertDialogService.provideDefaultOkDialog("Ok,Please Proceed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent(P_AddMedicineActivity.this, P_MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, null);
                } else {
                    mAlertDialogService.provideDefaultErrorDialog(databaseError.toString(), null);
                }
            }
        });
        mRemoteDbHelper.insert(p_medicineDetails);
    }

    private void setConfirmBtnOnClick() {
        mBinding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInputDetails();
                insertMedicineRemote();
            }
        });
    }
}
