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

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.data.MedicineDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.HashMap;

public class SetDetailFragment extends Fragment {


    View mView;
    AutoCompleteTextView drugStoreAutoEditText, medicineAutoEditText;
    EditText pillNumberEditText, frequencyDayEditText, frequencyIntervalEditText;
    Button mConfirmButton;
    String drugStore, medicineName;
    int pillNumber, frequencyDay, frequencyInterval;
    private MobileServiceClient mClient;


    public SetDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        TODO:Find out why autocomplete does not work with binding
        mView = inflater.inflate(R.layout.fragment_set_medicine_detail, container, false);

        try {
            mClient = new MobileServiceClient("https://smartdrugbox.azurewebsites.net", getContext());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        setDrugStoreFilter();
        setMedicineFilter();
        setPillNumberEditText();
        setFrequencyEditText();
        setConfirmBtn();

        return mView;

    }

    public void setDrugStoreFilter() {
        String[] testing = new String[]{"Drugstore1", "Drugstore2", "Drugstore3", "Drugstore4", "Drugstore5",};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, testing);
        drugStoreAutoEditText = (AutoCompleteTextView) mView.findViewById(R.id.editText_drugStore);
        drugStoreAutoEditText.setAdapter(adapter);
    }

    public void setMedicineFilter() {
        String[] testing = new String[]{"Antibiotic", "Panadol", "Gastric", "Stomache", "Toothache"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, testing);
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

    public void setConfirmBtn() {
        mConfirmButton = (Button) mView.findViewById(R.id.btn_confirm);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllEditTextInfo();
                sendDataToRemote();
                Log.d("Set Detail Fragment", "DrugStore: " + drugStore);
                Log.d("Set Detail Fragment", "Medicine Name: " + medicineName);
                Log.d("Set Detail Fragment", "Pill Number: " + String.valueOf(pillNumber));
                Log.d("Set Detail Fragment", "Day freqeuncy: " + String.valueOf(frequencyDay));
                Log.d("Set Detail Fragment", "Interval frequency: " + String.valueOf(frequencyInterval));
            }
        });
    }

    public void sendDataToRemote() {
        MedicineDetails medicineDetails = new MedicineDetails();
        medicineDetails.setDrugstore(drugStore);
        medicineDetails.setMedicineName(medicineName);
        medicineDetails.setPillNumberPurchase(pillNumber);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Day", frequencyDay);
        hashMap.put("Interval", frequencyInterval);
        medicineDetails.setTakeMedicineFrequency(convertToJsonString(hashMap));
        mClient.getTable(MedicineDetails.class).insert(medicineDetails, new TableOperationCallback<MedicineDetails>() {
            @Override
            public void onCompleted(MedicineDetails entity, Exception exception, ServiceFilterResponse response) {
                if (exception == null) {
                    Log.d("SetDetailFragment", "Insert Successful");
                } else {
                    Log.d("SetDetailFragment", "Insert Fail");
                }
            }
        });
    }

    public <T> String convertToJsonString(HashMap<String, T> hashMap) {
        JSONObject jsonObject = new JSONObject();
        Object[] keys = hashMap.keySet().toArray();
        try {
            for (Object key : keys) {
                jsonObject.put(key.toString(), hashMap.get(key));
            }
            Log.d("SetDetailFragment", jsonObject.toString());
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error Object";
        }
    }

}
