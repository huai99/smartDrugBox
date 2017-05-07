package com.siehuai.smartdrugbox.Pharmacy.view.P_EditCatalogue;

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
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IRemoteDbHelper;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbFactory;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;
import com.siehuai.smartdrugbox.Pharmacy.view.P_EditTabActivity;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.ActivityPEditCatalogueDetailBinding;

import static com.siehuai.smartdrugbox.Generic.common.Utils.safeParseDouble;
import static com.siehuai.smartdrugbox.Generic.common.Utils.safeParseInteger;

public class P_EditCatalogueDetailActivity extends AppCompatActivity {

    P_MedicineDetails medicineDetails;
    ActivityPEditCatalogueDetailBinding mBinding;
    private String medicineName;
    private double price;
    private String description;
    private int frequencyOfTaking;
    private String medicineMoreInfo;
    private String medicineImage;
    private Bitmap mBitmap;
    private boolean mShowStatus;
    private AlertDialogService mAlertDialogService;
    private IRemoteDbHelper mRemoteDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        medicineDetails = i.getParcelableExtra("P_MedicineDetails");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_edit_catalogue_detail);
        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(this);
        mRemoteDbHelper = RemoteDbFactory.createRemoteDbHelper(RemoteDbFactory.RemoteDataType.PharmacyMedicineDetails);

        initData();
        initUI();
        setChgImageOnClick();
        setEditBtnClick();
        setDeleteBtnClick();
    }

    public void initData() {
        medicineName = medicineDetails.getMedicineName();
        price = medicineDetails.getPrice();
        description = medicineDetails.getDescription();
        frequencyOfTaking = medicineDetails.getFrequencyOfTaking();
        medicineMoreInfo = medicineDetails.getMedicineMoreInfo();
        medicineImage = medicineDetails.getMedicineImage();
        mBitmap = Utils.Base64toBitMap(medicineImage);
        mShowStatus = medicineDetails.isShowStatus();
    }

    public void initUI() {
        mBinding.editTextName.setText(medicineName);
        mBinding.editTextPrice.setText(String.valueOf(price));
        mBinding.editTextDescription.setText(description);
        mBinding.editTextFrequencyOfTaking.setText(String.valueOf(frequencyOfTaking));
        mBinding.editTextMoreInfo.setText(medicineMoreInfo);
        mBinding.imgMedicine.setImageBitmap(mBitmap);
        setRadioBtn();
    }

    private void getAllInputDetails() {
        medicineName = mBinding.editTextName.getText().toString();
        price = safeParseDouble(mBinding.editTextPrice.getText().toString());
        description = mBinding.editTextDescription.getText().toString();
        frequencyOfTaking = safeParseInteger(mBinding.editTextFrequencyOfTaking.getText().toString());
        medicineMoreInfo = mBinding.editTextMoreInfo.getText().toString();
        medicineImage = Utils.BitMaptoBase64(this, mBitmap);
        mShowStatus = mBinding.radioBtnShow.isChecked();
    }

    private void editImage() {
        mAlertDialogService.provideCustomDialog("Choose method to get the image", "Edit Image",
                "Gallery", "Take Photo", R.drawable.camera,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.chooseImageFromMediaStore(P_EditCatalogueDetailActivity.this);
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.cameraCaptureImg(P_EditCatalogueDetailActivity.this);
                    }
                });
    }

    private void setChgImageOnClick() {
        mBinding.imgMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editImage();
            }
        });
    }

    private void setRadioBtn() {
        if (mShowStatus) {
            mBinding.radioBtnShow.setChecked(true);
        } else {
            mBinding.radioBtnHide.setChecked(true);
        }
    }

    private void setNewImageBitMap() {
        mBinding.imgMedicine.setImageBitmap(mBitmap);
    }

    private void setEditBtnClick() {
        mBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInputDetails();
                updateRemoteData();
            }
        });
    }

    private void setDeleteBtnClick() {
        mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRemoteData();
            }
        });
    }

    private void updateDataIntoObject() {
        medicineDetails.setMedicineName(medicineName);
        medicineDetails.setMedicineImage(medicineImage);
        medicineDetails.setMedicineMoreInfo(medicineMoreInfo);
        medicineDetails.setPrice(price);
        medicineDetails.setDescription(description);
        medicineDetails.setFrequencyOfTaking(frequencyOfTaking);
        medicineDetails.setShowStatus(mShowStatus);
    }

    private void setRemoteDbHelperListener() {
        mRemoteDbHelper.attachOnCompleteListener(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    mAlertDialogService.provideDefaultOkDialog("Ok,Please Proceed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent(P_EditCatalogueDetailActivity.this, P_EditTabActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }, null);
                } else {
                    mAlertDialogService.provideDefaultErrorDialog(databaseError.toString(), null);
                }
            }
        });
    }

    private void updateRemoteData() {
        updateDataIntoObject();
        setRemoteDbHelperListener();
        mRemoteDbHelper.update(medicineDetails);
    }

    private void deleteRemoteData() {
        setRemoteDbHelperListener();
        mRemoteDbHelper.delete(medicineDetails);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IOnActivityResultResponse response = OnActivityResultResponseFactory.createResponse(this, requestCode);
        response.execute(resultCode, data);
        mBitmap = (Bitmap) response.returnProcessResult();
        setNewImageBitMap();
    }
}

