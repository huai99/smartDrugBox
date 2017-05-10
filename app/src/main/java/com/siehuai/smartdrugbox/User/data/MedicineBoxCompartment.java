package com.siehuai.smartdrugbox.User.data;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

import java.util.HashMap;

public class MedicineBoxCompartment implements IDbData {

    private HashMap<String, CompartmentDetails> compartmentDetailsMap = new HashMap<>();
    private String id;


    public MedicineBoxCompartment() {
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public HashMap<String, CompartmentDetails> getCompartmentDetailsMap() {
        return compartmentDetailsMap;
    }

    public void setCompartmentDetailsMap(HashMap<String, CompartmentDetails> compartmentDetailsMap) {
        this.compartmentDetailsMap = compartmentDetailsMap;
    }
}
