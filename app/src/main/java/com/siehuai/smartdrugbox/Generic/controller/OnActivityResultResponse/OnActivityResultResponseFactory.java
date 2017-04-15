package com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse;

import android.app.Activity;

import com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.OnActivityResultResponseImpl.GetImgFrmGalleryResponse;
import com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse.OnActivityResultResponseImpl.TakePictureResponse;
import com.siehuai.smartdrugbox.Generic.data.GlobalConst;

public class OnActivityResultResponseFactory {

    public static IOnActivityResultResponse createResponse(Activity mActivity, int request) {
        if (request == GlobalConst.REQUEST_GET_FROM_MEDIA) {
            return new GetImgFrmGalleryResponse(mActivity);
        } else {
            return new TakePictureResponse(mActivity);
        }
    }
}
