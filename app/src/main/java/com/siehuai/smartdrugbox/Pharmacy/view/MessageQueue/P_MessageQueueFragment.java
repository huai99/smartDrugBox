package com.siehuai.smartdrugbox.Pharmacy.view.MessageQueue;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.Adapter.ListAdapterImpl.MessageQueueListAdapter;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.ListResource.ListResourceImpl.MessageQueueListResource;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;
import com.siehuai.smartdrugbox.Generic.data.Message;
import com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper.DaggerP_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper.P_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper.P_MessageQueueRemoteHelper;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.FragmentPMessageQueueBinding;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

public class P_MessageQueueFragment extends Fragment {


    FragmentPMessageQueueBinding mBinding;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Inject
    P_MessageQueueRemoteHelper mQueueRemoteHelper;

    @Inject
    MessageQueueListAdapter adapter;

    @Inject
    MessageQueueListResource listResource;

    public P_MessageQueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        P_DependencyInjectionComponent remoteHelperComponent = DaggerP_DependencyInjectionComponent.create();
        remoteHelperComponent.inject(this);

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_p_message_queue, container, false);

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
        mQueueRemoteHelper.findAll(new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                Collection<Message> changedList = (Collection<Message>) data;
                listResource.setResourceList(Utils.convertCollectionToArrayList(changedList));
                adapter.setResourceArrayList(listResource);
                adapter.notifyDataSetChanged();
                setAllMessageAsRead(Utils.convertCollectionToArrayList((ArrayList<Message>) listResource.getResourceList()));
            }
        });
        return listResource;
    }

    private void setAllMessageAsRead(ArrayList<Message> messageList) {
        for (Message message : messageList) {
            message.setReadStatus(true);
            mQueueRemoteHelper.update(message);
        }
    }

}
