package com.siehuai.smartdrugbox.User.view.OrderMedicine;


import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.HTTPHelper.IResponseReturnListener;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnCompleteListener;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.MedicineOrderRemoteHelper;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogService;
import com.siehuai.smartdrugbox.Generic.controller.Service.AlertDialogServiceFactory;
import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.OrderMedicine.CheckMedicineHelper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineDetails;
import com.siehuai.smartdrugbox.databinding.FragmentOrderMedicineBinding;

import java.util.ArrayList;

public class OrderMedicineFragment extends Fragment {

    FragmentOrderMedicineBinding mBinding;
    private String name;
    private String drugStore;
    private String frequency;
    private String price;
    private String description;
    private String moreInfo;
    private AlertDialogService mAlertDialogService;
    private Bitmap mBitmap;
    MedicineDetails mMedicineDetails;
    RequestQueue queue;
    CheckMedicineHelper mCheckMedicineHelper;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    public OrderMedicineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlertDialogService = AlertDialogServiceFactory.createAlertDialogService(getActivity());
        queue = Volley.newRequestQueue(getActivity());
        mCheckMedicineHelper = new CheckMedicineHelper(queue);
        Intent i = getActivity().getIntent();
        CompartmentDetails compartmentDetails = i.getParcelableExtra("CompartmentDetails");
        mMedicineDetails = compartmentDetails.getMedicineDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_order_medicine, container, false);
        View view = mBinding.getRoot();
        if (mMedicineDetails != null) {
            initData();
            initView();
            promptAlertDialog();
        } else {
            promptErrorDialog();
        }
        return view;
    }

    private void initData() {
        name = mMedicineDetails.getMedicineName();
        drugStore = mMedicineDetails.getDrugstore();
        frequency = String.valueOf(mMedicineDetails.getFrequencyOfTaking());
        price = String.valueOf(mMedicineDetails.getPrice());
        description = mMedicineDetails.getDescription();
        moreInfo = mMedicineDetails.getMedicineMoreInfo();
        mBitmap = Utils.Base64toBitMap(mMedicineDetails.getMedicineImage());
    }


    private void initView() {
        mBinding.editTextName.setText(name);
        mBinding.editTextDrugStore.setText(drugStore);
        mBinding.editTextFrequency.setText(frequency + " times/day");
        mBinding.editTextPrice.setText("$" + price);
        mBinding.editTextDescription.setText(description);
        mBinding.editTextMoreInfo.setText(moreInfo);
        mBinding.imageViewMedicine.setImageBitmap(mBitmap);
    }

    private void promptAlertDialog() {
        mAlertDialogService.provideCustomDialog(
                "This medicine has run out",
                "Order online from the drugstore nearby",
                "Ok",
                "Cancel",
                R.drawable.high_priority,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        promptOptionsDialog();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
    }

    private void promptErrorDialog() {
        mAlertDialogService.provideDefaultErrorDialog("Message Details should not be empty, please contact 8285 to find out more info ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private void promptOptionsDialog() {
        mAlertDialogService.provideCustomDialog(
                "Choose your preferred way of buying medicine",
                "Options",
                "System manage",
                "Self Manage",
                R.drawable.high_priority,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        systemManageOrderMedicine();
                        dialog.cancel();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inAnimation = new AlphaAnimation(0f, 1f);
                        inAnimation.setDuration(200);
                        mBinding.progressBarHolder.setAnimation(inAnimation);
                        mBinding.progressBarHolder.setVisibility(View.VISIBLE);
                        selfManageOrderMedicine();
                        dialog.cancel();
                    }
                }
        );
    }

    private void systemManageOrderMedicine() {
        MedicineOrderRemoteHelper remoteHelper = MedicineOrderRemoteHelper.getInstance();
        String id = remoteHelper.generateNewId();
        MedicineOrder medicineOrder = new MedicineOrder(id, "Sie Huai", "", "8284", mMedicineDetails, true, null, false, false);
        remoteHelper.insert(medicineOrder, new IDbOnCompleteListener() {
            @Override
            public void onComplete(Object error) {
                if (error == null) {
                    promptSuccessfulAlertDialog();
                } else {
                    promptErrorAlertDialog((String) error);
                }
            }
        });
    }

    private void selfManageOrderMedicine() {
        mCheckMedicineHelper.sendRequestToViewPharmacyMenu(mMedicineDetails.getMedicineName(),
                new IResponseReturnListener() {
                    @Override
                    public void onResponseComplete(Object response) {
                        ArrayList<MedicineDetails> medicineDetailsList = (ArrayList<MedicineDetails>) response;
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("medicineDetailsList", medicineDetailsList);
                        ViewRequestedMedicineMenuFragment requestedMedicineMenuFragment = new ViewRequestedMedicineMenuFragment();
                        requestedMedicineMenuFragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_order_medicine, requestedMedicineMenuFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        outAnimation = new AlphaAnimation(1f, 0f);
                        outAnimation.setDuration(200);
                        mBinding.progressBarHolder.setAnimation(outAnimation);
                        mBinding.progressBarHolder.setVisibility(View.GONE);
                    }
                });
    }

    private void promptSuccessfulAlertDialog() {
        mAlertDialogService.provideDefaultOkDialog("We will notified you when a pharmacy has accepted your order",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }

    private void promptErrorAlertDialog(String errorMessage) {
        mAlertDialogService.provideDefaultErrorDialog(errorMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }


}
