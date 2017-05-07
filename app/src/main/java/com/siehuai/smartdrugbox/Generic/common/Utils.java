package com.siehuai.smartdrugbox.Generic.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Base64;

import com.siehuai.smartdrugbox.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.siehuai.smartdrugbox.Generic.data.GlobalConst.REQUEST_GET_FROM_MEDIA;
import static com.siehuai.smartdrugbox.Generic.data.GlobalConst.REQUEST_TAKE_PHOTO;

public class Utils {


    public static String convertListToString(ArrayList<String> list) {
        StringBuilder strBuffer = new StringBuilder();
        for (String msg : list) {
            strBuffer.append(msg + "\n");
        }
        return strBuffer.toString();
    }

    public static Double safeParseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return (double) -1.00;
        }
    }

    public static Integer safeParseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void chooseImageFromMediaStore(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, REQUEST_GET_FROM_MEDIA);
    }

    public static void cameraCaptureImg(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    public static String BitMaptoBase64(Context mContext, Bitmap mBitMap) {
        if (mBitMap == null) {
            mBitMap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.placeholder);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mBitMap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    public static Bitmap Base64toBitMap(String encodedImage) {
        try {
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (Exception e) {
            return null;
        }
    }
}
