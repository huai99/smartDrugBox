package com.siehuai.smartdrugbox.User.view.MedicineBox;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.Adapter.MedicineBoxMenuAdapter;
import com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.MedicineBox.ViewMedicineBoxMenuOnClickListener;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxDetailsRemoteHelper;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;
import com.siehuai.smartdrugbox.User.data.MenuResource.MedicineBoxMenuResource;
import com.siehuai.smartdrugbox.databinding.FragmentViewMedicineBoxMenuBinding;

import java.util.ArrayList;
import java.util.Collection;

public class ViewMedicineBoxMenuFragment extends Fragment {


    FragmentViewMedicineBoxMenuBinding mBinding;
    MedicineBoxMenuAdapter adapter;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    public ViewMedicineBoxMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_medicine_box_menu, container, false);

        View view = mBinding.getRoot();

        setUpGridView(view);

        setUpFab();

        return view;
    }

    private void setUpGridView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecycleViewLayoutManager();
        adapter = new MedicineBoxMenuAdapter();
        adapter.setResourceArrayList(setUpMenuResource());
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

    private void setUpFab() {
        mBinding.fabAddMedicineBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMedicineBoxDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    public MenuResource setUpMenuResource() {
        final MenuResource resource = new MedicineBoxMenuResource();
        ArrayList<MedicineBoxDetails> list = new ArrayList<>();
        resource.setResourceList(list);
        MedicineBoxDetailsRemoteHelper remoteHelper = MedicineBoxDetailsRemoteHelper.getInstance();
        remoteHelper.findAll(new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                Collection<MedicineBoxDetails> changedList = (Collection<MedicineBoxDetails>) data;
                resource.setResourceList(Utils.convertCollectionToArrayList(changedList));
                adapter.setResourceArrayList(resource);
                adapter.notifyDataSetChanged();
            }
        });
        setupMenuOnClickListener();
        return resource;
    }

    private void setupMenuOnClickListener() {
        adapter.addObserver(new ViewMedicineBoxMenuOnClickListener(getContext(), this));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Choose patient");
    }
}
