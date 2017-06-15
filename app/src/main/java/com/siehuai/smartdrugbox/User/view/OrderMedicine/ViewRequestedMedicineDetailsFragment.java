package com.siehuai.smartdrugbox.User.view.OrderMedicine;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.FragmentViewRequestedMedicineDetailsBinding;

public class ViewRequestedMedicineDetailsFragment extends Fragment {

    FragmentViewRequestedMedicineDetailsBinding mBinding;

    public ViewRequestedMedicineDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_requested_medicine_details, container, false);
        View view = mBinding.getRoot();
        return view;
    }

}
