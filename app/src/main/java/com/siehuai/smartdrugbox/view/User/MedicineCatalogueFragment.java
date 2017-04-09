package com.siehuai.smartdrugbox.view.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.User.Adapter.MedicineDetailsRenderAdapter;
import com.siehuai.smartdrugbox.data.User.MenuResource.MedicineDetailsResource;

import java.util.ArrayList;

public class MedicineCatalogueFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicine_catalogue, container, false);

        setUpGridView(view);

        return view;
    }

    public void setUpGridView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecycleViewLayoutManager();
        MedicineDetailsRenderAdapter adapter = new MedicineDetailsRenderAdapter();
        MedicineDetailsResource resource = new MedicineDetailsResource();
        resource.setResourceImgList(new ArrayList() {
            {
                add(R.drawable.medicine_box_icon);
            }

            {
                add(R.drawable.placeholder);
            }

            {
                add(R.drawable.placeholder);
            }

            {
                add(R.drawable.placeholder);
            }
        });
        resource.setResourceTextList(new ArrayList<String>() {
            {
                add("Sie Huai");
            }

            {
                add("Sie Huai");
            }

            {
                add("Sie Huai");
            }

            {
                add("Sie Huai");
            }
        });
        adapter.setResourceArrayList(resource);
        mRecyclerView.setAdapter(adapter);
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

}
