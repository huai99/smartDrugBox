package com.siehuai.smartdrugbox.Generic.controller.RemoteDatabaseHelper;


import com.siehuai.smartdrugbox.Pharmacy.view.P_ViewMedicineOrder.P_ViewMedicineOrderDetailsFragment;

import dagger.Component;

@Component(modules = GenericRemoteHelperModule.class)
public interface GenericRemoteHelperComponent {
    void inject(P_ViewMedicineOrderDetailsFragment fragment);
}
