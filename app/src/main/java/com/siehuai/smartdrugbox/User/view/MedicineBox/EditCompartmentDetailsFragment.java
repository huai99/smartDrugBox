package com.siehuai.smartdrugbox.User.view.MedicineBox;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.RemoteDbFactory;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.MedicineBoxCompartmentRemoteHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.User.data.U_PharmacyDetails;

public class EditCompartmentDetailsFragment extends Fragment {

    EditText medicineNameEditText;
    EditText drugStoreEditText;
    Button confirmBtn;
    private MedicineBoxCompartmentRemoteHelper mMedicineBoxCompartmentRemoteHelper;
    private AlertDialogService mAlertDialogService;
    private String medicineName;
    private String drugStoreName;
    CompartmentDetails mCompartmentDetails;
    MedicineDetails mMedicineDetails;

    public EditCompartmentDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCompartmentDetails = bundle.getParcelable("compartmentDetails");
        Log.d("EditCompartmentDetails", String.valueOf(mCompartmentDetails));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_compartment_details, container, false);
        medicineNameEditText = (EditText) view.findViewById(R.id.editText_medicineName);
        drugStoreEditText = (EditText) view.findViewById(R.id.editText_drugStore);
        confirmBtn = (Button) view.findViewById(R.id.btn_confirm);
        mMedicineBoxCompartmentRemoteHelper
                = (MedicineBoxCompartmentRemoteHelper) RemoteDbFactory
                .createRemoteDbHelper(RemoteDbFactory.RemoteDataType.CompartmentDetails);
        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(getActivity());
        mMedicineDetails = mCompartmentDetails.getMedicineDetails();

        initData();
        initView();
        setConfirmBtnOnClick();
        return view;
    }

    private void initData() {
        if (checkIfMedicineAvailable(mCompartmentDetails.getMedicineDetails())) {
            medicineName = mMedicineDetails.getMedicineName();
            drugStoreName = mMedicineDetails.getDrugstore();
        }
    }

    private void initView() {
        medicineNameEditText.setText(medicineName);
        drugStoreEditText.setText(drugStoreName);
    }

    private boolean checkIfMedicineAvailable(MedicineDetails medicineDetails) {
        if (medicineDetails == null) {
            return false;
        } else {
            return true;
        }
    }

    private void setConfirmBtnOnClick() {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInputDetails();
                editCompartmentDetailsRemote();
            }
        });
    }

    private void getAllInputDetails() {
        medicineName = medicineNameEditText.getText().toString();
        drugStoreName = drugStoreEditText.getText().toString();
    }


    private void editCompartmentDetailsRemote() {
        MedicineDetails newMedicineDetails = createMedicineDetails();
        setCompartmentDetails(newMedicineDetails);
        mMedicineBoxCompartmentRemoteHelper.attachOnCompleteListener(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    mAlertDialogService.provideDefaultOkDialog("Ok,Please Proceed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ViewMedicineBoxMenuFragment medicineBoxMenuFragment = new ViewMedicineBoxMenuFragment();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_edit_compartment_details, medicineBoxMenuFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    }, null);
                } else {
                    mAlertDialogService.provideDefaultErrorDialog(databaseError.toString(), null);
                }
            }
        });
        mMedicineBoxCompartmentRemoteHelper.update(mCompartmentDetails);
    }

    private MedicineDetails createMedicineDetails() {
        MedicineDetails medicineDetails = new MedicineDetails();
        String id = null;
        medicineDetails.setId(id);
        medicineDetails.setMedicineName(medicineName);
        U_PharmacyDetails pharmacyDetails = new U_PharmacyDetails();
        pharmacyDetails.setPharmacyName(drugStoreName);
        medicineDetails.setPharmacyDetails(pharmacyDetails);
        return medicineDetails;
    }

    private void setCompartmentDetails(MedicineDetails medicineDetails) {
        mCompartmentDetails.setMedicineDetails(medicineDetails);
        mCompartmentDetails.setFillUpStatus(true);
    }

}
