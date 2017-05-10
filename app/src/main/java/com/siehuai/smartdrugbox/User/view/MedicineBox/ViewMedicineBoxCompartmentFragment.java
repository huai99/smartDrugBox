package com.siehuai.smartdrugbox.User.view.MedicineBox;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.Adapter.MedicineBoxCompartmentMenuAdapter;
import com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.MedicineBox.CompartmentFragmentOnClickListener;
import com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper.MedicineBoxCompartmentLocalDataHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;
import com.siehuai.smartdrugbox.User.data.MenuResource.CompartmentDetailsMenuResource;
import com.siehuai.smartdrugbox.databinding.FragmentViewMedicineBoxCompartmentBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class ViewMedicineBoxCompartmentFragment extends Fragment {

    MedicineBoxCompartmentMenuAdapter adapter;
    protected RecyclerView mRecyclerView;
    FragmentViewMedicineBoxCompartmentBinding mBinding;
    protected RecyclerView.LayoutManager mLayoutManager;
    MedicineBoxDetails mMedicineBoxDetails;

    public ViewMedicineBoxCompartmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_medicine_box_compartment, container, false);

        View view = mBinding.getRoot();

        setUpGridView(view);

        return view;
    }

    private void setUpGridView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecycleViewLayoutManager();
        adapter = new MedicineBoxCompartmentMenuAdapter();
        adapter.addObserver(new CompartmentFragmentOnClickListener(this));
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
        final MenuResource resource = new CompartmentDetailsMenuResource();
        final MedicineBoxCompartmentLocalDataHelper localDataHelper = MedicineBoxCompartmentLocalDataHelper.getInstance();
        Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                ArrayList<MedicineBoxCompartment> compartments = (ArrayList<MedicineBoxCompartment>) localDataHelper.returnAppData();
                Collection<CompartmentDetails> values = compartments.get(0).getCompartmentDetailsMap().values();
                ArrayList<CompartmentDetails> resourceList = new ArrayList<>();
                for (CompartmentDetails compartmentDetails : values) {
                    resourceList.add(compartmentDetails);
                }
                resource.setResourceList(resourceList);
                adapter.notifyDataSetChanged();
            }
        };
        localDataHelper.addObserver(observer);
        ArrayList<MedicineBoxCompartment> compartments = (ArrayList<MedicineBoxCompartment>) localDataHelper.returnAppData();
        Collection<CompartmentDetails> values = compartments.get(0).getCompartmentDetailsMap().values();
        ArrayList<CompartmentDetails> resourceList = new ArrayList<>();
        for (CompartmentDetails compartmentDetails : values) {
            resourceList.add(compartmentDetails);
        }
        resource.setResourceList(resourceList);
        return resource;
    }

}
