package com.siehuai.smartdrugbox.User.view.OrderMedicine;


import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnCompleteListener;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.Generic.data.PharmacyDetails;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.DaggerU_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.User.controller.U_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.databinding.FragmentViewRequestedMedicineDetailsBinding;

import javax.inject.Inject;

public class ViewRequestedMedicineDetailsFragment extends Fragment {

    FragmentViewRequestedMedicineDetailsBinding mBinding;
    MedicineDetails mMedicineDetails;
    String pharmacyName;
    String price;
    String frequencyOfTaking;
    String medicineName;
    String description;
    String medicineMoreInfo;
    Bitmap mBitmap;
    PharmacyDetails mPharmacyDetails;
    AlertDialogService mAlertDialogService;

    @Inject
    MedicineOrderRemoteHelper mMedicineOrderRemoteHelper;

    public ViewRequestedMedicineDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mMedicineDetails = bundle.getParcelable("medicineDetails");
        Log.d("Requested Medicine", String.valueOf(mMedicineDetails));
        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(getContext());
        U_DependencyInjectionComponent uDependencyInjectionComponent = DaggerU_DependencyInjectionComponent.create();
        uDependencyInjectionComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_requested_medicine_details, container, false);
        View view = mBinding.getRoot();
        if (mMedicineDetails != null) {
            initData();
            initView();
            setUpSendOrderBtn();
        }
        return view;
    }

    private void initData() {

        pharmacyName = mMedicineDetails.getDrugstore();
        price = String.valueOf(mMedicineDetails.getPrice());
        frequencyOfTaking = String.valueOf(mMedicineDetails.getFrequencyOfTaking());
        medicineName = String.valueOf(mMedicineDetails.getMedicineName());
        description = mMedicineDetails.getDescription();
        medicineMoreInfo = mMedicineDetails.getMedicineMoreInfo();
        mBitmap = Utils.Base64toBitMap(mMedicineDetails.getMedicineImage());
        mPharmacyDetails = mMedicineDetails.getPharmacyDetails();
    }


    private void initView() {
        mBinding.editTextPharmacyName.setText(pharmacyName);
        mBinding.editTextPrice.setText("$ " + price);
        mBinding.editTextFrequencyOfTaking.setText(frequencyOfTaking + " times/day");
        mBinding.editTextMedicineName.setText(medicineName);
        mBinding.editTextDescription.setText(description);
        mBinding.editTextMoreInfo.setText(medicineMoreInfo);
        mBinding.imageViewMedicine.setImageBitmap(mBitmap);
    }

    private void setUpSendOrderBtn() {
        mBinding.btnSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mMedicineOrderRemoteHelper.generateNewId();
                MedicineOrder medicineOrder =
                        new MedicineOrder(id, "Sie Huai", "", "8284", mMedicineDetails, true, mPharmacyDetails, true, false);
                mMedicineOrderRemoteHelper.insert(medicineOrder, new IDbOnCompleteListener() {
                    @Override
                    public void onComplete(Object error) {
                        if (error == null) {
                            promptSuccessfulAlertDialog();
                        } else {
                            promptErrorAlertDialog((String) error);
                        }
                    }
                });
            }
        });
    }

    private void promptSuccessfulAlertDialog() {
        mAlertDialogService.provideDefaultOkDialog("Order successfully make, go back to main page?",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }

    private void promptErrorAlertDialog(String errorMessage) {
        mAlertDialogService.provideDefaultErrorDialog(errorMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

}
