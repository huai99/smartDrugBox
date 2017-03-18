package com.siehuai.smartdrugbox.view.userSetMedicine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.siehuai.smartdrugbox.R;

public class SetDetailFragment extends Fragment {


    View mView;

    public SetDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        TODO:Find out why autocomplete does not work with binding
//        mBinding = DataBindingUtil.inflate(
//                inflater,
//                R.layout.fragment_set_detail,
//                container,
//                false);
        mView = inflater.inflate(R.layout.fragment_set_detail, container, false);
        setEditTextFilter();


        return mView;

    }

    public void setEditTextFilter() {
        String[] testing = new String[]{"Tiger", "Ted", "Yahni", "soap", "Cat", "FAFA",};
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, testing);
        AutoCompleteTextView textView = (AutoCompleteTextView) mView.findViewById(R.id.editText_drugStore);
        textView.setAdapter(adapter);
//        mBinding.editTextDrugStore.setAdapter(adapter);
    }

}
