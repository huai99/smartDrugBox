package com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder;


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
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;
import com.siehuai.smartdrugbox.Pharmacy.controller.OnClickListener.P_ViewMedicineOrder.MedicineOrderListClickListener;
import com.siehuai.smartdrugbox.Pharmacy.controller.P_MedicineOrderIListAdapter;
import com.siehuai.smartdrugbox.Pharmacy.data.ListResource.P_MedicineOrderListResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.FragmentPViewMedicineOrderBinding;

import java.util.Collection;

public class P_ViewMedicineOrderListFragment extends Fragment {

    FragmentPViewMedicineOrderBinding mBinding;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    P_MedicineOrderIListAdapter adapter;

    public P_ViewMedicineOrderListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_p_view_medicine_order, container, false);

        View view = mBinding.getRoot();

        setUpListView(view);

        return view;
    }

    public void setUpListView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        setRecycleViewLayoutManager();
        adapter = new P_MedicineOrderIListAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setResourceArrayList(setupListResource());
        adapter.addObserver(new MedicineOrderListClickListener(this));
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
        final IListResource resource = new P_MedicineOrderListResource();
        final MedicineOrderRemoteHelper remoteHelper = MedicineOrderRemoteHelper.getInstance();
        remoteHelper.findAll(new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                Collection<MedicineOrder> changedList = (Collection<MedicineOrder>) data;
                resource.setResourceList(Utils.convertCollectionToArrayList(changedList));
                Log.d("View Medicine Order", String.valueOf(resource.getResourceSize()));
                adapter.setResourceArrayList(resource);
                adapter.notifyDataSetChanged();
            }
        });
        return resource;
    }

}
