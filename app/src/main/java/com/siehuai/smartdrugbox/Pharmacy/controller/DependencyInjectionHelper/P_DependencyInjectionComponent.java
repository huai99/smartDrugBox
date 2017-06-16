package com.siehuai.smartdrugbox.Pharmacy.controller.DependencyInjectionHelper;

import com.siehuai.smartdrugbox.Pharmacy.view.P_MainActivity;
import com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder.P_ViewMedicineOrderDetailsFragment;

import dagger.Component;

@Component(modules = P_DependencyInjectionModule.class)
public interface P_DependencyInjectionComponent {

    void inject(P_MainActivity mainActivity);

    void inject(P_ViewMedicineOrderDetailsFragment fragment);
}
