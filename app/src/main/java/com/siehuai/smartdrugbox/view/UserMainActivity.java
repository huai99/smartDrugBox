package com.siehuai.smartdrugbox.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.controller.PostsDatabaseHelper;
import com.siehuai.smartdrugbox.data.AlarmData;
import com.siehuai.smartdrugbox.data.NetworkAddress;
import com.siehuai.smartdrugbox.databinding.ActivityUserMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserMainActivity extends AppCompatActivity {
    ActivityUserMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_main);

        PostsDatabaseHelper postsDbHelper = PostsDatabaseHelper.getInstance(this);

        initAlarmData(postsDbHelper);

        setReminderBtn();

        setOrderMedicineBtn();

        activateAlarm();
    }

    public void setReminderBtn() {

        mBinding.btnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReminder();
            }
        });
    }

    public void setOrderMedicineBtn() {
        mBinding.btnOrderMed.setOnClickListener(new View.OnClickListener() {
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
        // Instantiate the RequestQueue.

        startActivity(intent);
    }

    public void activateAlarm() {

        mBinding.btnTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(UserMainActivity.this);
                String url = NetworkAddress.ESP8266;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String rsp = response.toString();
                                Log.d("UserMainActivity", rsp);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Hello world", "/BUZ");
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }

    public void initAlarmData(PostsDatabaseHelper helper) {

        ArrayList<AlarmData> alarmArrayList = helper.getAllAlarmFrmDb();

        helper.updateAlarmInLocal(alarmArrayList);
    }
}
