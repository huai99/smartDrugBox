package com.siehuai.smartdrugbox.User.data;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class MedicineBoxCombineDetails implements IDbData {

    MedicineBoxDetails mMedicineBoxDetails;
    MedicineBoxCompartmentDetails mMedicineBoxCompartmentDetails;

    @Override
    public void setId(String id) {

    }

    @Override
    public String getId() {
        return null;
    }

    public MedicineBoxDetails getMedicineBoxDetails() {
        return mMedicineBoxDetails;
    }

    public void setMedicineBoxDetails(MedicineBoxDetails medicineBoxDetails) {
        mMedicineBoxDetails = medicineBoxDetails;
    }

    public MedicineBoxCompartmentDetails getMedicineBoxCompartmentDetails() {
        return mMedicineBoxCompartmentDetails;
    }

    public void setMedicineBoxCompartmentDetails(MedicineBoxCompartmentDetails medicineBoxCompartmentDetails) {
        mMedicineBoxCompartmentDetails = medicineBoxCompartmentDetails;
    }
}
