package com.siehuai.smartdrugbox.controller.HardwareController.HardwareControllerImpl;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.siehuai.smartdrugbox.controller.HardwareController.HardwareController;
import com.siehuai.smartdrugbox.data.NetworkAddress;

import java.util.HashMap;
import java.util.Map;

public class BuzzerController implements HardwareController {

    RequestQueue queue;
    public static String LOG_TAG = "Buzzer Controller";
    Response.Listener<String> response;
    Response.ErrorListener errorResponse;


    public BuzzerController(Context context) {
        queue = Volley.newRequestQueue(context);
        initReturnResponse();
    }

    @Override
    public void turnOn() {
        turnOnBuzzer();
    }

    @Override
    public void turnOff() {
        turnOffBuzzer();
    }

    private void initReturnResponse() {
        response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(BuzzerController.LOG_TAG, response.toString());
            }
        };

        errorResponse = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(BuzzerController.LOG_TAG, error.toString());
            }
        };


    }

    private void turnOnBuzzer() {
        StringRequest buzzRequest = new StringRequest(Request.Method.POST, NetworkAddress.ESP8266_BUZ,
                response, errorResponse) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Hello world", "/BUZ");
                return params;
            }
        };
        queue.add(buzzRequest);
    }

    private void turnOffBuzzer() {
        StringRequest buzzRequest = new StringRequest(Request.Method.POST, NetworkAddress.ESP8266_STOP,
                response, errorResponse) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Hello world", "/STOP");
                return params;
            }
        };
        queue.add(buzzRequest);
    }
}
