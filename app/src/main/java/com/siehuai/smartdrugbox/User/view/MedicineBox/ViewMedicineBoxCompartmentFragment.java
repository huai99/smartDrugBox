package com.siehuai.smartdrugbox.User.view.MedicineBox;


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
import com.siehuai.smartdrugbox.User.controller.Adapter.MedicineBoxMenuAdapter;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;
import com.siehuai.smartdrugbox.User.data.MenuResource.MedicineBoxCompartmentMenuResource;
import com.siehuai.smartdrugbox.databinding.FragmentViewMedicineBoxCompartmentBinding;

public class ViewMedicineBoxCompartmentFragment extends Fragment {

    MedicineBoxMenuAdapter adapter;
    protected RecyclerView mRecyclerView;
    FragmentViewMedicineBoxCompartmentBinding mBinding;
    protected RecyclerView.LayoutManager mLayoutManager;
    MedicineBoxDetails mMedicineBoxDetails;

    public ViewMedicineBoxCompartmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mMedicineBoxDetails = bundle.getParcelable("MedicineBoxDetails");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_medicine_box_menu, container, false);

        View view = mBinding.getRoot();

        setUpGridView(view);

        return view;
    }

    private void setUpGridView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecycleViewLayoutManager();
        adapter = new MedicineBoxMenuAdapter();
        MenuResource resource = setUpMenuResource();
        adapter.setResourceArrayList(resource);
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
        final MenuResource resource = new MedicineBoxCompartmentMenuResource();
//        resource.setResourceList(mMedicineBoxDetails.getCompartmentDetails());
        return resource;
    }

}
