package com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.OnActivityResultResponseImpl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.IOnActivityResultResponse;
import com.siehuai.smartdrugbox.R;

public class TakePictureResponse implements IOnActivityResultResponse {

    Bitmap mImageBitMap;
    Activity mActivity;

    public TakePictureResponse(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void execute(int resultCode, Intent data) {
        if (data != null && resultCode == mActivity.RESULT_OK) {
            Bundle extras = data.getExtras();
            mImageBitMap = (Bitmap) extras.get("data");
        } else {
            mImageBitMap = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.placeholder);
        }
    }

    @Override
    public Object returnProcessResult() {
        return mImageBitMap;
    }
}
