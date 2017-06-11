package com.siehuai.smartdrugbox.Pharmacy.controller.RemoteDatabaseHelper;

import com.siehuai.smartdrugbox.Pharmacy.view.P_MainActivity;

import dagger.Component;

@Component(modules = P_RemoteHelperModule.class)
public interface RemoteHelperComponent {

    void inject(P_MainActivity mainActivity);

}
