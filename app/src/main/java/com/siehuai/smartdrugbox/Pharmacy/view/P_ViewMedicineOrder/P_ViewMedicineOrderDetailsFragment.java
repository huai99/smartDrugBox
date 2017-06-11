package com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder;


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
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.databinding.FragmentPViewMedicineOrderDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class P_ViewMedicineOrderDetailsFragment extends Fragment {

    MedicineOrder mMedicineOrder;
    FragmentPViewMedicineOrderDetailsBinding mBinding;
    AlertDialogService mAlertDialogService;
    MedicineDetails mMedicineDetails;
    String userName;
    String address;
    String contactNumber;
    String medicineName;
    String description;
    String medicineMoreInfo;
    String medicineImg;
    Bitmap mBitmap;

    public P_ViewMedicineOrderDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mMedicineOrder = bundle.getParcelable("order");
        Log.d("ViewOrderDetails", String.valueOf(mMedicineOrder));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_p_view_medicine_order_details, container, false);

        View view = mBinding.getRoot();

        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(getActivity());
        mMedicineDetails = mMedicineOrder.getMedicineDetails();
        if (mMedicineDetails != null) {
            initData();
            initView();
        } else {
            promptErrorDialog();
        }

        return view;
    }

    private void initData() {
        userName = mMedicineOrder.getUserName();
        address = mMedicineOrder.getAddress();
        contactNumber = mMedicineOrder.getContact();
        medicineName = String.valueOf(mMedicineDetails.getMedicineName());
        description = mMedicineDetails.getDescription();
        medicineMoreInfo = mMedicineDetails.getMedicineMoreInfo();
        mBitmap = Utils.Base64toBitMap(mMedicineDetails.getMedicineImg());
    }


    private void initView() {
        mBinding.editTextUserName.setText(userName);
        mBinding.editTextUserAddress.setText(address);
        mBinding.editTextContactNumber.setText(contactNumber);
        mBinding.editTextMedicineName.setText(medicineName);
        mBinding.editTextDescription.setText(description);
        mBinding.editTextMoreInfo.setText(medicineMoreInfo);
        mBinding.imageViewMedicine.setImageBitmap(mBitmap);
    }

    private void promptErrorDialog() {
        mAlertDialogService.provideDefaultErrorDialog("Message Details should not be empty, please contact developer to find out more info ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

}
