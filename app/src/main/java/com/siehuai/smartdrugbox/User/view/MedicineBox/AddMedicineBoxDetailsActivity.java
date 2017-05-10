package com.siehuai.smartdrugbox.User.view.MedicineBox;

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
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxDetailsRemoteHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;
import com.siehuai.smartdrugbox.databinding.ActivityAddMedicineBoxDetailsBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class AddMedicineBoxDetailsActivity extends AppCompatActivity {

    private ActivityAddMedicineBoxDetailsBinding mBinding;
    private String userName;
    private String userImg;
    private int compartmentNumber;
    private String emergencyContact;
    private MedicineBoxDetailsRemoteHelper mMedicineBoxDetailsRemoteHelper;
    private MedicineBoxCompartmentRemoteHelper mMedicineBoxCompartmentRemoteHelper;
    private AlertDialogService mAlertDialogService;
    private ArrayList<String> errorMsgList = new ArrayList<>();
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_box_details);
        mMedicineBoxDetailsRemoteHelper
                = (MedicineBoxDetailsRemoteHelper) RemoteDbFactory
                .createRemoteDbHelper(RemoteDbFactory.RemoteDataType.MedicineBoxDetails);

        mMedicineBoxCompartmentRemoteHelper
                = (MedicineBoxCompartmentRemoteHelper) RemoteDbFactory
                .createRemoteDbHelper(RemoteDbFactory.RemoteDataType.CompartmentDetails);

        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_medicine_box_details);
        setConfirmBtnOnClick();
        setChgImageOnClick();
    }

    private void setConfirmBtnOnClick() {
        mBinding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInputDetails();
                insertMedicineBoxRemote();
            }
        });
    }

    private void getAllInputDetails() {
        userName = mBinding.editTextUserName.getText().toString();
        userImg = Utils.BitMaptoBase64(this, mBitmap);
        emergencyContact = mBinding.editTextEmergencyContact.getText().toString();
        compartmentNumber = Utils.safeParseInteger(mBinding.editTextTotalCompartmentNumber.getText().toString());
    }

    private void editImage() {
        mAlertDialogService.provideCustomDialog("Choose method to get the image", "Edit Image",
                "Gallery", "Take Photo", R.drawable.camera,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.chooseImageFromMediaStore(AddMedicineBoxDetailsActivity.this);
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.cameraCaptureImg(AddMedicineBoxDetailsActivity.this);
                    }
                });
    }

    private void setChgImageOnClick() {
        mBinding.imgNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editImage();
            }
        });
    }

    private void insertMedicineBoxRemote() {
        MedicineBoxDetails medicineBoxDetails = new MedicineBoxDetails(null, userName, userImg, compartmentNumber, 0, emergencyContact);
        mMedicineBoxDetailsRemoteHelper.attachOnCompleteListener(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    mAlertDialogService.provideDefaultOkDialog("Ok,Please Proceed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent(AddMedicineBoxDetailsActivity.this, MedicineBoxActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }, null);
                } else {
                    mAlertDialogService.provideDefaultErrorDialog(databaseError.toString(), null);
                }
            }
        });
        String id = mMedicineBoxCompartmentRemoteHelper.generateNewId();

        //Set the same id for detail and compartment so they can find each other
        mMedicineBoxCompartmentRemoteHelper.setKey(id);
        mMedicineBoxDetailsRemoteHelper.setKey(id);
        mMedicineBoxDetailsRemoteHelper.insert(medicineBoxDetails);
        MedicineBoxCompartment compartment = createInitialCompartment(compartmentNumber, id);
        mMedicineBoxCompartmentRemoteHelper.insert(compartment);
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
        mBinding.imgNewUser.setImageBitmap(mBitmap);
    }

    private MedicineBoxCompartment createInitialCompartment(int compartmentNumber, String id) {
        MedicineBoxCompartment compartment = new MedicineBoxCompartment();
        HashMap<String, CompartmentDetails> map = new HashMap<>();

        for (int i = 1; i < compartmentNumber + 1; i++) {
            String key = "Compartment " + i;
            CompartmentDetails compartmentDetails = new CompartmentDetails();
            compartmentDetails.setId(String.valueOf(i));
            compartmentDetails.setMedicineBoxId(id);
            map.put(key, compartmentDetails);
        }
        compartment.setCompartmentDetailsMap(map);
        return compartment;
    }
}
