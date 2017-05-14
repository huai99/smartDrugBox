package com.siehuai.smartdrugbox.User.view.MedicineBox;


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
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.databinding.FragmentViewCompartmentDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCompartmentDetailsFragment extends Fragment {

    FragmentViewCompartmentDetailsBinding mBinding;
    CompartmentDetails mCompartmentDetails;
    private String name;
    private String drugStore;
    private String frequency;
    private String price;
    private String description;
    private String moreInfo;
    private AlertDialogService mAlertDialogService;
    private Bitmap mBitmap;
    MedicineDetails mMedicineDetails;

    public ViewCompartmentDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCompartmentDetails = bundle.getParcelable("compartmentDetails");
        mMedicineDetails = mCompartmentDetails.getMedicineDetails();
        Log.d("EditCompartmentDetails", String.valueOf(mCompartmentDetails));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_compartment_details, container, false);

        View view = mBinding.getRoot();

        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(getContext());

        initData();

        initView();

        return view;
    }

    private void initData() {
        if (checkIfMedicineAvailable(mMedicineDetails)) {
            name = mMedicineDetails.getMedicineName();
            drugStore = mMedicineDetails.getDrugstore();
            frequency = mMedicineDetails.getFrequencyOfTaking();
            price = String.valueOf(mMedicineDetails.getPrice());
            description = mMedicineDetails.getDescription();
            moreInfo = mMedicineDetails.getMedicineMoreInfo();
            mBitmap = Utils.Base64toBitMap(mMedicineDetails.getMedicineImg());
        }
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

    private boolean checkIfMedicineAvailable(MedicineDetails medicineDetails) {
        if (medicineDetails == null) {
            mAlertDialogService.provideDefaultErrorDialog("Compartment is not filled up yet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    getFragmentManager().popBackStack();
                }
            });
            return false;
        } else {
            return true;
        }
    }

}
