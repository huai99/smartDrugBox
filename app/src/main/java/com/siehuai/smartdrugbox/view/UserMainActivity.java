package com.siehuai.smartdrugbox.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.PostsDatabaseHelper;

public class UserMainActivity extends AppCompatActivity {

    Button setReminderBtn, orderMedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_main);

        PostsDatabaseHelper postsDbHelper = PostsDatabaseHelper.getInstance(this);

        postsDbHelper.getAllAlarm();

        setReminderBtn = (Button) findViewById(R.id.btn_setReminder);

        orderMedBtn = (Button) findViewById(R.id.btn_orderMed);

        setReminderBtn();

        setOrderMedicineBtn();
    }

    public void setReminderBtn() {

        setReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReminder();
            }
        });
    }

    public void setOrderMedicineBtn() {
        orderMedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderMedicine();
            }
        });
    }

    public void setReminder() {
        Intent intent = new Intent(UserMainActivity.this, UserSetReminderActivity.class);
        startActivity(intent);
    }

    public void orderMedicine() {
        Intent intent = new Intent(UserMainActivity.this, UserViewMedicineTabActivity.class);
        startActivity(intent);

    }
}
