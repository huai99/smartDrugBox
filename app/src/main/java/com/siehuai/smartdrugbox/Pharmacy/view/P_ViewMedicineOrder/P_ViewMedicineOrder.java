package com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;
import com.siehuai.smartdrugbox.Pharmacy.controller.P_MedicineOrderIListAdapter;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineOrderListResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.ActivityPViewMedicineOrderBinding;

import java.util.Collection;

public class P_ViewMedicineOrder extends AppCompatActivity {

    ActivityPViewMedicineOrderBinding mBinding;

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    P_MedicineOrderIListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_view_medicine_order);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_view_medicine_order);
        setUpListView();
    }

    public void setUpListView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setRecycleViewLayoutManager();
        adapter = new P_MedicineOrderIListAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setResourceArrayList(setupListResource());
//        adapter.addObserver(new P_EditCatalogueClickListener(getActivity()));
    }

    public void setRecycleViewLayoutManager() {
        int scrollPosition = 0;
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new LinearLayoutManager(this);
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
