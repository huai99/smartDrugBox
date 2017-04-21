package com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.OnActivityResultResponseImpl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.IOnActivityResultResponse;

import java.io.FileNotFoundException;

public class GetImgFrmGalleryResponse implements IOnActivityResultResponse {

    Bitmap mBitmap;
    Activity mActivity;

    public GetImgFrmGalleryResponse(Activity activity) {
        mActivity = activity;
    }

    @TargetApi(20)
    @Override
    public void execute(int resultCode, Intent data) {

        Uri targetUri = data.getData();
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            mBitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(targetUri), null, options);
            int size = mBitmap.getAllocationByteCount();
            int total = 1;
            while (size > 300000) {
                options.inSampleSize = 2 * total;
                mBitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(targetUri), null, options);
                size = mBitmap.getAllocationByteCount();
                total = total * 2;
            }
            Log.d("GetImgFrmGallery", "Current Size: " + String.valueOf(size));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object returnProcessResult() {
        return mBitmap;
    }
}
