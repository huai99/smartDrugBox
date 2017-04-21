package com.siehuai.smartdrugbox.Pharmacy.view.P_EditCatalogue;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.Pharmacy.controller.LocalAppDataHelper.P_MedicineDetailsLocalDataHelper;
import com.siehuai.smartdrugbox.Pharmacy.controller.P_MedicineDetailsMenuAdapter;
import com.siehuai.smartdrugbox.Pharmacy.data.MenuResource.P_MedicineDetailsMenuResource;
import com.siehuai.smartdrugbox.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 */
public class P_EditCatalogueFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    P_MedicineDetailsMenuAdapter adapter;
    ProgressBar mProgressBar;


    public P_EditCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_edit_catalogue, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        hideProgressBar();
        setUpGridView(view);
        return view;
    }

    public void setUpGridView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecycleViewLayoutManager();
        adapter = new P_MedicineDetailsMenuAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setResourceArrayList(setUpMenuResource());
        adapter.addObserver(new P_EditCatalogueClickListener(getActivity()));
    }

    public void setRecycleViewLayoutManager() {
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
        final MenuResource resource = new P_MedicineDetailsMenuResource();
        final P_MedicineDetailsLocalDataHelper localDataHelper = P_MedicineDetailsLocalDataHelper.getInstance();
        Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                resource.setResourceList((ArrayList<?>) localDataHelper.returnAppData());
                adapter.notifyDataSetChanged();
                hideProgressBar();
            }
        };
        localDataHelper.addObserver(observer);
        ArrayList<?> resourceList = (ArrayList<?>) localDataHelper.returnAppData();
        resource.setResourceList(resourceList);
        if (resourceList.size() == 0) {
            showProgressBar();
        }
        return resource;
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}
