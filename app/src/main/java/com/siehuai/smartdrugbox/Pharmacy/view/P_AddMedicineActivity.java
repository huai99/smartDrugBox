package com.siehuai.smartdrugbox.Pharmacy.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.IOnActivityResultResponse;
import com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.OnActivityResultResponseFactory;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbFactory;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.ActivityPAddMedicineBinding;

import java.util.ArrayList;

import static com.siehuai.smartdrugbox.Generic.common.Utils.safeParseDouble;
import static com.siehuai.smartdrugbox.Generic.common.Utils.safeParseInteger;

public class P_AddMedicineActivity extends AppCompatActivity {

    private ActivityPAddMedicineBinding mBinding;
    private String medicineName;
    private double price;
    private String description;
    private int frequencyOfTaking;
    private String medicineMoreInfo;
    private String medicineImage;
    private RemoteDbHelper mRemoteDbHelper;
    private AlertDialogService mAlertDialogService;
    private ArrayList<String> errorMsgList = new ArrayList<>();
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_add_medicine);
        mRemoteDbHelper = RemoteDbFactory.createRemoteDbHelper(RemoteDbFactory.RemoteDataType.PharmacyMedicineDetails);
        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_add_medicine);
        setConfirmBtnOnClick();
        setChgImageOnClick();
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

    private void getAllInputDetails() {
        medicineName = mBinding.editTextName.getText().toString();
        price = safeParseDouble(mBinding.editTextPrice.getText().toString());
        description = mBinding.editTextDescription.getText().toString();
        frequencyOfTaking = safeParseInteger(mBinding.editTextFrequencyOfTaking.getText().toString());
        medicineMoreInfo = mBinding.editTextMoreInfo.getText().toString();
        medicineImage = Utils.BitMaptoBase64(this, mBitmap);
    }

    private void editImage() {
        mAlertDialogService.provideCustomDialog("Choose method to get the image", "Edit Image",
                "Gallery", "Take Photo", R.drawable.successful,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.chooseImageFromMediaStore(P_AddMedicineActivity.this);
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.cameraCaptureImg(P_AddMedicineActivity.this);
                    }
                });
    }

    private void setChgImageOnClick() {
        mBinding.imgNewMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editImage();
            }
        });
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
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }, null);
                } else {
                    mAlertDialogService.provideDefaultErrorDialog(databaseError.toString(), null);
                }
            }
        });
        mRemoteDbHelper.insert(p_medicineDetails);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IOnActivityResultResponse response = OnActivityResultResponseFactory.createResponse(this, requestCode);
        response.execute(resultCode, data);
        mBitmap = (Bitmap) response.returnProcessResult();
        setNewImageBitMap();
    }

    private void setNewImageBitMap() {
        mBinding.imgNewMedicine.setImageBitmap(mBitmap);
    }
}
