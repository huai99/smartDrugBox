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

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.Adapter.MedicineBoxCompartmentMenuAdapter;
import com.siehuai.smartdrugbox.User.controller.BtnOnClickListener.MedicineBox.CompartmentFragmentOnClickListener;
import com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper.MedicineBoxCompartmentLocalDataHelper;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;
import com.siehuai.smartdrugbox.User.data.MenuResource.CompartmentDetailsMenuResource;
import com.siehuai.smartdrugbox.databinding.FragmentViewMedicineBoxCompartmentBinding;

import java.util.ArrayList;
import java.util.Collection;

public class ViewMedicineBoxCompartmentFragment extends Fragment {

    MedicineBoxCompartmentMenuAdapter adapter;
    protected RecyclerView mRecyclerView;
    FragmentViewMedicineBoxCompartmentBinding mBinding;
    protected RecyclerView.LayoutManager mLayoutManager;
    MedicineBoxDetails mMedicineBoxDetails;
    MedicineBoxCompartmentRemoteHelper mRemoteHelper;
    MedicineBoxCompartmentLocalDataHelper mLocalDataHelper;
    String medicineBoxDetailsId;

    public ViewMedicineBoxCompartmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        medicineBoxDetailsId = bundle.getString("id");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_view_medicine_box_compartment, container, false);

        View view = mBinding.getRoot();

        mRemoteHelper = new MedicineBoxCompartmentRemoteHelper();

        mRemoteHelper.read();

        mLocalDataHelper = MedicineBoxCompartmentLocalDataHelper.getInstance();

        setUpGridView(view);

        return view;
    }

    private void setUpGridView(View mView) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecycleViewLayoutManager();
        adapter = new MedicineBoxCompartmentMenuAdapter();
        adapter.addObserver(new CompartmentFragmentOnClickListener(this));
        mRecyclerView.setAdapter(adapter);
        setUpResourceInAdapter();
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

    public void setUpResourceInAdapter() {
        final MenuResource resource = new CompartmentDetailsMenuResource();
        final ArrayList<CompartmentDetails> resourceList = new ArrayList<>();
        resource.setResourceList(resourceList);
        adapter.setResourceArrayList(resource);
        mLocalDataHelper.find(medicineBoxDetailsId, new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                resourceList.clear();
                MedicineBoxCompartment compartments = (MedicineBoxCompartment) data;
                if (compartments != null) {
                    Collection<CompartmentDetails> values = compartments.getCompartmentDetailsMap().values();
                    resource.setResourceList(Utils.convertCollectionToArrayList(values));
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
