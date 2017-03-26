package com.siehuai.smartdrugbox.view.UserSetMedicine;


import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.common.Utils;
import com.siehuai.smartdrugbox.common.Validation;
import com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.DatabaseConnectionHelper;
import com.siehuai.smartdrugbox.controller.RemoteDatabaseHelper.TableDataHelper.MedicineDetailsRemoteHelper;
import com.siehuai.smartdrugbox.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.view.UserMainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SetDetailFragment extends Fragment {


    View mView;
    AutoCompleteTextView drugStoreAutoEditText, medicineAutoEditText;
    EditText pillNumberEditText, frequencyDayEditText, frequencyIntervalEditText, compartmentEditText;
    Button mConfirmButton;
    String drugStore, medicineName;
    int pillNumber, frequencyDay, frequencyInterval, compartmentNumber;
    MedicineDetailsRemoteHelper mMedicineDetailsRemoteHelper;
    ArrayList<String> errorMessageList = new ArrayList<String>();
    AlertDialogService mAlertDialogService;
    DatabaseConnectionHelper connectionHelper;

    public SetDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        TODO:Find out why autocomplete does not work with binding
        mView = inflater.inflate(R.layout.fragment_set_medicine_detail, container, false);

        mAlertDialogService = new AlertDialogService(getContext());

        mMedicineDetailsRemoteHelper = new MedicineDetailsRemoteHelper();

        connectionHelper = new DatabaseConnectionHelper();

        setDrugStoreFilter();
        setMedicineFilter();
        setPillNumberEditText();
        setFrequencyEditText();
        setConfirmBtn();
        setCompartmentNumber();

        return mView;

    }

    private void setDrugStoreFilter() {
        //TODO:Get data from the cloud properly

        String[] testing = new String[]{"Drugstore1", "Drugstore2", "Drugstore3", "Drugstore4", "Drugstore5",};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, testing);
        drugStoreAutoEditText = (AutoCompleteTextView) mView.findViewById(R.id.editText_drugStore);
        drugStoreAutoEditText.setAdapter(adapter);
    }

    private void setMedicineFilter() {
        //TODO:Get data from the cloud properly
        String[] testing = new String[]{
                "Antibiotic", "Panadol", "Gastric", "Stomache", "Toothache", "Medicine 6"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, testing);
        medicineAutoEditText = (AutoCompleteTextView) mView.findViewById(R.id.editText_medicine);
        medicineAutoEditText.setAdapter(adapter);
    }

    private void setPillNumberEditText() {
        pillNumberEditText = (EditText) mView.findViewById(R.id.editText_pillNumber);
    }

    private void setFrequencyEditText() {
        frequencyDayEditText = (EditText) mView.findViewById(R.id.editText_perDayNumber);
    }

    private void setCompartmentNumber() {
        compartmentEditText = (EditText) mView.findViewById(R.id.editText_compartment_number);
    }

    private void getDrugStoreInfo() {
        drugStore = drugStoreAutoEditText.getText().toString();
    }

    private void getMedicineInfo() {
        medicineName = medicineAutoEditText.getText().toString();
    }

    private void getPillNumberInfo() {
        pillNumber = safeParseInteger(pillNumberEditText.getText().toString());
    }

    private void getFreqeuncyofTakingInfo() {
        frequencyDay = safeParseInteger(frequencyDayEditText.getText().toString());
        frequencyInterval = safeParseInteger("0");
    }

    private void getCompartmentNumberInfo() {
        compartmentNumber = safeParseInteger(compartmentEditText.getText().toString());
    }


    private void getAllEditTextInfo() {
        getDrugStoreInfo();
        getMedicineInfo();
        getPillNumberInfo();
        getFreqeuncyofTakingInfo();
        getCompartmentNumberInfo();
    }

    private void setConfirmBtn() {
        mConfirmButton = (Button) mView.findViewById(R.id.btn_confirm);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllEditTextInfo();
                if (isValidateInput() && isConnectedToDb()) {
                    insertMedicineRemote();
                } else {
                    mAlertDialogService.provideDefaultErrorDialog(Utils.convertListToString(errorMessageList),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    errorMessageList.clear();
                                    dialog.dismiss();
                                }
                            });
                }
                Log.d("Set Detail Fragment", "DrugStore: " + drugStore);
                Log.d("Set Detail Fragment", "Medicine Name: " + medicineName);
                Log.d("Set Detail Fragment", "Pill Number: " + String.valueOf(pillNumber));
                Log.d("Set Detail Fragment", "Day freqeuncy: " + String.valueOf(frequencyDay));
                Log.d("Set Detail Fragment", "Interval frequency: " + String.valueOf(frequencyInterval));
            }
        });
    }

    private <T> String convertToJsonString(HashMap<String, T> hashMap) {
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

    public Integer safeParseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            //TODO: Do validation for input afterwards
            return -1;
        }
    }

    public void insertMedicineRemote() {
        mMedicineDetailsRemoteHelper.attachOnCompleteListener(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    mAlertDialogService.provideDefaultErrorDialog("Database Error: " + databaseError.toString(),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                } else {
                    mAlertDialogService.provideDefaultOkDialog("Please proceed",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getContext(), UserMainActivity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            }, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                }
            }
        });
        mMedicineDetailsRemoteHelper.insert(drugStore, medicineName, pillNumber, compartmentNumber, "everyday");
    }

    public boolean isValidateInput() {
        if (!Validation.isPositiveInteger(pillNumber)) {
            errorMessageList.add("Pill Number: " + Validation.returnIsPositiveErrorMsg());
            return false;
        } else if (!Validation.isPositiveInteger(compartmentNumber)) {
            errorMessageList.add("Compartment Number: " + Validation.returnIsPositiveErrorMsg());
            return false;
        }
        return true;
    }

    public boolean isConnectedToDb() {
        if (!connectionHelper.isConnectionChecker()) {
            errorMessageList.add("No Connection to the Internet");
        }
        return connectionHelper.isConnectionChecker();
    }


}
