package com.siehuai.smartdrugbox.view.userViewMedicine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siehuai.smartdrugbox.R;

public class ViewMedicineFragment extends Fragment {


    public ViewMedicineFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_medicine, container, false);
    }

}
