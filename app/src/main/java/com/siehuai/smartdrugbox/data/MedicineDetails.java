package com.siehuai.smartdrugbox.data;


public class MedicineDetails {
    private String id;
    private String drugstore;
    private String medicineName;
    private int pillNumberPurchase;
    private String takeMedicineFrequency;

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
}
