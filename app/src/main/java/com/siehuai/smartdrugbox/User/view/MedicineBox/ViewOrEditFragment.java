package com.siehuai.smartdrugbox.User.view.MedicineBox;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.databinding.FragmentViewOrEditBinding;

public class ViewOrEditFragment extends Fragment {

    FragmentViewOrEditBinding mBinding;
    CompartmentDetails mCompartmentDetails;

    public ViewOrEditFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCompartmentDetails = bundle.getParcelable("compartmentDetails");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Compartment " + mCompartmentDetails.getId());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_or_edit, container, false);

        View view = mBinding.getRoot();

        setUpViewBtn();

        setUpEditBtn();

        return view;
    }

    private void setUpViewBtn() {
        mBinding.btnViewMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundl = new Bundle();
                bundl.putParcelable("compartmentDetails", mCompartmentDetails);
                ViewCompartmentDetailsFragment viewCompartmentFragment = new ViewCompartmentDetailsFragment();
                viewCompartmentFragment.setArguments(bundl);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_view_or_edit, viewCompartmentFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void setUpEditBtn() {
        mBinding.btnEditMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundl = new Bundle();
                bundl.putParcelable("compartmentDetails", mCompartmentDetails);
                EditCompartmentDetailsFragment compartmentFragment = new EditCompartmentDetailsFragment();
                compartmentFragment.setArguments(bundl);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_view_or_edit, compartmentFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
