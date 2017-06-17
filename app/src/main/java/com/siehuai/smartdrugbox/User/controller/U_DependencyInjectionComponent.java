package com.siehuai.smartdrugbox.User.controller;

import com.siehuai.smartdrugbox.User.view.MessageQueue.U_MessageQueueFragment;
import com.siehuai.smartdrugbox.User.view.OrderMedicine.ViewRequestedMedicineDetailsFragment;
import com.siehuai.smartdrugbox.User.view.U_MainActivity;

import dagger.Component;

@Component(modules = U_DependencyInjectionModule.class)
public interface U_DependencyInjectionComponent {

    void inject(ViewRequestedMedicineDetailsFragment fragment);

    void inject(U_MessageQueueFragment fragment);

    void inject(U_MainActivity activity);
}
