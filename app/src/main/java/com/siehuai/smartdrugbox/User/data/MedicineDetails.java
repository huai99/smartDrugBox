package com.siehuai.smartdrugbox.User.data;

import com.google.firebase.database.IgnoreExtraProperties;
import com.siehuai.smartdrugbox.Generic.data.IDbData;

@IgnoreExtraProperties
public class MedicineDetails implements IDbData {

    private String id;
    private String medicineName;
    private String drugstore;
    private String description;
    private String frequencyOfTaking;
    private int compartmentNumber;
    private String medicineImg;

    public MedicineDetails() {
    }

    public MedicineDetails(String id,
                           String medicineName,
                           String drugstore,
                           String description,
                           String frequencyOfTaking,
                           int compartmentNumber,
                           String medicineImg) {
        this.id = id;
        this.medicineName = medicineName;
        this.drugstore = drugstore;
        this.description = description;
        this.frequencyOfTaking = frequencyOfTaking;
        this.compartmentNumber = compartmentNumber;
        this.medicineImg = medicineImg;
    }

    public String getDrugstore() {
        return drugstore;
    }

    public void setDrugstore(String drugstore) {
        this.drugstore = drugstore;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequencyOfTaking() {
        return frequencyOfTaking;
    }

    public void setFrequencyOfTaking(String frequencyOfTaking) {
        this.frequencyOfTaking = frequencyOfTaking;
    }

    public int getCompartmentNumber() {
        return compartmentNumber;
    }

    public void setCompartmentNumber(int compartmentNumber) {
        this.compartmentNumber = compartmentNumber;
    }

    public String getMedicineImg() {
        return medicineImg;
    }

    public void setMedicineImg(String medicineImg) {
        this.medicineImg = medicineImg;
    }
}
