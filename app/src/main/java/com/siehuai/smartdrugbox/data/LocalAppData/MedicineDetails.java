package com.siehuai.smartdrugbox.data.LocalAppData;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MedicineDetails {
    private String id;
    private String drugstore;
    private String medicineName;
    private int pillNumberPurchase;
    private String takeMedicineFrequency;
    private int compartmentNumber;

    public MedicineDetails(String id,
                           String drugstore,
                           String medicineName,
                           int pillNumberPurchase,
                           int compartmentNumber,
                           String takeMedicineFrequency) {
        this.compartmentNumber = compartmentNumber;
        this.drugstore = drugstore;
        this.id = id;
        this.medicineName = medicineName;
        this.pillNumberPurchase = pillNumberPurchase;
        this.takeMedicineFrequency = takeMedicineFrequency;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getPillNumberPurchase() {
        return pillNumberPurchase;
    }

    public void setPillNumberPurchase(int pillNumberPurchase) {
        this.pillNumberPurchase = pillNumberPurchase;
    }

    public String getTakeMedicineFrequency() {
        return takeMedicineFrequency;
    }

    public void setTakeMedicineFrequency(String takeMedicineFrequency) {
        this.takeMedicineFrequency = takeMedicineFrequency;
    }

    public int getCompartmentNumber() {
        return compartmentNumber;
    }

    public void setCompartmentNumber(int compartmentNumber) {
        this.compartmentNumber = compartmentNumber;
    }
}
