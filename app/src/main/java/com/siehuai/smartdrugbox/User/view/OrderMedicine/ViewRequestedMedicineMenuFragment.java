package com.siehuai.smartdrugbox.User.view.OrderMedicine;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.Adapter.MedicineDetailsMenuAdapter;
import com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.OrderMedicine.ViewRequestedMedicineMenuOnClickListener;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.User.data.MenuResource.MedicineDetailsMenuResource;
import com.siehuai.smartdrugbox.databinding.FragmentViewRequestedMedicineMenuBinding;

import java.util.ArrayList;

public class ViewRequestedMedicineMenuFragment extends Fragment {

    FragmentViewRequestedMedicineMenuBinding mBinding;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private MedicineDetailsMenuAdapter adapter;
    private ArrayList<MedicineDetails> mMedicineDetailsList;


    public ViewRequestedMedicineMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mMedicineDetailsList = bundle.getParcelableArrayList("medicineDetailsList");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_requested_medicine_menu, container, false);

        View view = mBinding.getRoot();

        setUpGridView(view);

        return view;
    }

    private void setUpGridView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecycleViewLayoutManager();
        adapter = new MedicineDetailsMenuAdapter();
        adapter.setResourceArrayList(setUpMenuResource());
        adapter.addObserver(new ViewRequestedMedicineMenuOnClickListener(this));
        mRecyclerView.setAdapter(adapter);
    }

    private void setRecycleViewLayoutManager() {
        int scrollPosition = 0;
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    public MenuResource setUpMenuResource() {
        final MenuResource resource = new MedicineDetailsMenuResource();
        resource.setResourceList(mMedicineDetailsList);
        return resource;
    }


}
