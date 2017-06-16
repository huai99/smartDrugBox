package com.siehuai.smartdrugbox.Pharmacy.view.OrderQueue;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;
import com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper.DaggerP_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper.P_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.P_OrderQueueListAdapter;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_OrderQueueListRemoteHelper;
import com.siehuai.smartdrugbox.Pharmacy.data.ListResource.P_OrderQueueListResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.FragmentOrderQueueBinding;

import java.util.Collection;

import javax.inject.Inject;

public class OrderQueueFragment extends Fragment {

    FragmentOrderQueueBinding mBinding;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Inject
    P_OrderQueueListAdapter adapter;

    @Inject
    P_OrderQueueListResource listResource;

    @Inject
    P_OrderQueueListRemoteHelper queueListRemoteHelper;

    public OrderQueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        P_DependencyInjectionComponent remoteHelperComponent = DaggerP_DependencyInjectionComponent.create();
        remoteHelperComponent.inject(this);

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_order_queue, container, false);

        View view = mBinding.getRoot();

        setUpListView(view);

        return view;
    }

    public void setUpListView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        setRecycleViewLayoutManager();
        mRecyclerView.setAdapter(adapter);
        adapter.setResourceArrayList(setupListResource());
    }

    public void setRecycleViewLayoutManager() {
        int scrollPosition = 0;
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    public IListResource setupListResource() {
        queueListRemoteHelper.findAll(new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                Collection<MedicineOrder> changedList = (Collection<MedicineOrder>) data;
                listResource.setResourceList(Utils.convertCollectionToArrayList(changedList));
                Log.d("View Medicine Order", String.valueOf(listResource.getResourceSize()));
                adapter.setResourceArrayList(listResource);
                adapter.notifyDataSetChanged();
            }
        });
        return listResource;
    }
}
