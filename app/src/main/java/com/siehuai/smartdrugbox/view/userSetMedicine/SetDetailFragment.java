package com.siehuai.smartdrugbox.view.userSetMedicine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.siehuai.smartdrugbox.R;

public class SetDetailFragment extends Fragment {


    View mView;
    AutoCompleteTextView drugStoreAutoEditText, medicineAutoEditText;
    EditText pillNumberEditText, frequencyDayEditText, frequencyIntervalEditText;
    Button mConfirmButton;
    String drugStore, medicineName;
    int pillNumber, frequencyDay, frequencyInterval;

    public SetDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        TODO:Find out why autocomplete does not work with binding
        mView = inflater.inflate(R.layout.fragment_set_medicine_detail, container, false);

        setDrugStoreFilter();
        setMedicineFilter();
        setPillNumberEditText();
        setFrequencyEditText();
        setConfirmBtn();

        return mView;

    }

    public void setDrugStoreFilter() {
        String[] testing = new String[]{"Drugstore1", "Drugstore2", "Drugstore3", "Drugstore4", "Drugstore5",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, testing);
        drugStoreAutoEditText = (AutoCompleteTextView) mView.findViewById(R.id.editText_drugStore);
        drugStoreAutoEditText.setAdapter(adapter);
    }

    public void setMedicineFilter() {
        String[] testing = new String[]{"Antibiotic", "Panadol", "Gastric", "Stomache", "Toothache"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, testing);
        medicineAutoEditText = (AutoCompleteTextView) mView.findViewById(R.id.editText_medicine);
        medicineAutoEditText.setAdapter(adapter);
    }

    public void setPillNumberEditText() {
        pillNumberEditText = (EditText) mView.findViewById(R.id.editText_pillNumber);
    }

    public void setFrequencyEditText() {
        frequencyDayEditText = (EditText) mView.findViewById(R.id.editText_perDayNumber);
        frequencyIntervalEditText = (EditText) mView.findViewById(R.id.editText_interval);
    }

    public void setConfirmBtn() {
        mConfirmButton = (Button) mView.findViewById(R.id.btn_confirm);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllEditTextInfo();
                Log.d("Set Detail Fragment", "DrugStore: " + drugStore);
                Log.d("Set Detail Fragment", "Medicine Name: " + medicineName);
                Log.d("Set Detail Fragment", "Pill Number: " + String.valueOf(pillNumber));
                Log.d("Set Detail Fragment", "Day freqeuncy: " + String.valueOf(frequencyDay));
                Log.d("Set Detail Fragment", "Interval frequency: " + String.valueOf(frequencyInterval));
            }
        });
    }

    public void getDrugStoreInfo() {
        drugStore = drugStoreAutoEditText.getText().toString();
    }

    public void getMedicineInfo() {
        medicineName = medicineAutoEditText.getText().toString();
    }

    public void getPillNumberInfo() {
        pillNumber = Integer.valueOf(pillNumberEditText.getText().toString());
    }

    public void getFreqeuncyofTakingInfo() {
        frequencyDay = Integer.valueOf(frequencyDayEditText.getText().toString());
        frequencyInterval = Integer.valueOf(frequencyIntervalEditText.getText().toString());
    }

    public void getAllEditTextInfo() {
        getDrugStoreInfo();
        getMedicineInfo();
        getPillNumberInfo();
        getFreqeuncyofTakingInfo();
    }

}
