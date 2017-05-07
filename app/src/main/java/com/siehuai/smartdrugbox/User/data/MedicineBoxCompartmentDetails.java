package com.siehuai.smartdrugbox.User.data;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

import java.util.HashMap;

public class MedicineBoxCompartmentDetails implements IDbData {

    private HashMap<String, MedicineDetails> map = new HashMap<>();
    private String id;


    public MedicineBoxCompartmentDetails() {
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public HashMap<String, MedicineDetails> getMap() {
        return map;
    }

    public void setMap(HashMap<String, MedicineDetails> map) {
        this.map = map;
    }

}
