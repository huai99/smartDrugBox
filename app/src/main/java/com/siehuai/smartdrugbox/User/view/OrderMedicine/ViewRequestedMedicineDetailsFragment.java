package com.siehuai.smartdrugbox.User.view.OrderMedicine;


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
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.databinding.FragmentViewRequestedMedicineDetailsBinding;

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

    public ViewRequestedMedicineDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mMedicineDetails = bundle.getParcelable("medicineDetails");
        Log.d("Requested Medicine", String.valueOf(mMedicineDetails));
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

}
