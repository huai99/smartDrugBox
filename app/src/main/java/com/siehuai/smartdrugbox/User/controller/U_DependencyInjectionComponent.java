package com.siehuai.smartdrugbox.User.controller;

import com.siehuai.smartdrugbox.User.view.OrderMedicine.ViewRequestedMedicineDetailsFragment;

import dagger.Component;

@Component(modules = U_DependencyInjectionModule.class)
public interface U_DependencyInjectionComponent {

    void inject(ViewRequestedMedicineDetailsFragment fragment);
}
