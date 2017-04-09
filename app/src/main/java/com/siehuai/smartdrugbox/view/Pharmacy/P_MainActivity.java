package com.siehuai.smartdrugbox.view.Pharmacy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.databinding.ActivityPMainBinding;

public class P_MainActivity extends AppCompatActivity {

    ActivityPMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_main);
        setUpEditCatalogueBtnOnClick();
        setUpAddMedicineBtnOnClick();
    }

    private void setUpEditCatalogueBtnOnClick() {
        mBinding.btnEditCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P_MainActivity.this, P_EditTabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpAddMedicineBtnOnClick() {
        mBinding.btnAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P_MainActivity.this, P_AddMedicineActivity.class);
                startActivity(intent);
            }
        });
    }


}
