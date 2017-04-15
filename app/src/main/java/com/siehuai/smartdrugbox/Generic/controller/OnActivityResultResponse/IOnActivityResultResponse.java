package com.siehuai.smartdrugbox.Generic.controller.OnActivityResultResponse;

import android.content.Intent;

public interface IOnActivityResultResponse {
    public void execute(int resultCode, Intent data);

    public Object returnProcessResult();
}
