package com.siehuai.smartdrugbox.User.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.Message;
import com.siehuai.smartdrugbox.Generic.data.NetworkAddress;
import com.siehuai.smartdrugbox.Generic.view.FragmentDrawer;
import com.siehuai.smartdrugbox.R;
import com.siehuai.smartdrugbox.User.controller.DaggerU_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.User.controller.RemoteDatabaseHelper.UserMessageQueueRemoteHelper;
import com.siehuai.smartdrugbox.User.controller.U_DependencyInjectionComponent;
import com.siehuai.smartdrugbox.User.view.MedicineBox.MedicineBoxActivity;
import com.siehuai.smartdrugbox.User.view.MessageQueue.U_MessageQueueActivity;
import com.siehuai.smartdrugbox.User.view.UserViewMedicine.UserViewMedicineActivity;
import com.siehuai.smartdrugbox.databinding.ActivityUserMainBinding;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class U_MainActivity extends U_BaseActivity {

    ActivityUserMainBinding mBinding;

    @Inject
    UserMessageQueueRemoteHelper mMessageQueueRemoteHelper;

    MenuItem mMenuItem;

    private FragmentDrawer drawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        U_DependencyInjectionComponent remoteHelperComponent = DaggerU_DependencyInjectionComponent.create();

        remoteHelperComponent.inject(this);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_main);

        setSetMedicineBtn();

        activateAlarm();

        mToolbar = (Toolbar) mBinding.drawerLayout.findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(new FragmentDrawer.FragmentDrawerListener() {
            @Override
            public void onDrawerItemSelected(View view, int position) {

            }
        });
    }

    public void setSetMedicineBtn() {
        mBinding.btnOrderMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSetMedicine();
            }
        });
    }

    public void orderMedicine() {
        Intent intent = new Intent(U_MainActivity.this, UserViewMedicineTabActivity.class);
        startActivity(intent);
    }

    public void activateAlarm() {

        mBinding.btnTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(U_MainActivity.this);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkAddress.ESP8266_BUZ,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("U_MainActivity", response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("Hello world", "/BUZ");
                        return params;
                    }
                };
                // Add the request to the RequestQueue.

                queue.add(stringRequest);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_main, menu);
        mMenuItem = menu.findItem(R.id.message_queue);
        fetchMessageFrmRemote();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.option_viewMedicine):
                userViewMedicine();
                return true;
            case R.id.message_queue:
                goToMessageQueue();
                return true;
            default:
                return false;
        }
    }


    public void userSetMedicine() {
        Intent intent = new Intent(U_MainActivity.this, MedicineBoxActivity.class);
        startActivity(intent);
    }

    public void userViewMedicine() {
        Intent intent = new Intent(U_MainActivity.this, UserViewMedicineActivity.class);
        startActivity(intent);
    }

    private void goToMessageQueue() {
        Intent intent = new Intent(this, U_MessageQueueActivity.class);
        startActivity(intent);
    }

    private void fetchMessageFrmRemote() {
        mMessageQueueRemoteHelper.findAll(new IDbOnDataChangeListener() {
            @Override
            public void onDataChange(Object data) {
                Collection<Message> changedList = (Collection<Message>) data;
                mMenuItem.setIcon(R.drawable.message_color);
                for (Message message : changedList) {
                    if (!message.isReadStatus()) {
                        mMenuItem.setIcon(R.drawable.urgent_message_color);
                        break;
                    }
                }
            }
        });
    }
}
