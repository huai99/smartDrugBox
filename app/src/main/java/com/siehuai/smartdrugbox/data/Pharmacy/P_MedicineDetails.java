package com.siehuai.smartdrugbox.data.Pharmacy;

import com.siehuai.smartdrugbox.data.IRemoteDbData;

public class P_MedicineDetails implements IRemoteDbData {

    private String id;
    private String medicineName;
    private double price;
    private String description;
    private int frequencyOfTaking;
    private String medicineMoreInfo;
    private int medicineImage;

    public P_MedicineDetails(String medicineName,
                             double price,
                             String description,
                             int frequencyOfTaking,
                             String medicineMoreInfo,
                             int medicineImage) {
        this.medicineName = medicineName;
        this.price = price;
        this.description = description;
        this.frequencyOfTaking = frequencyOfTaking;
        this.medicineMoreInfo = medicineMoreInfo;
        this.medicineImage = medicineImage;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFrequencyOfTaking() {
        return frequencyOfTaking;
    }

    public void setFrequencyOfTaking(int frequencyOfTaking) {
        this.frequencyOfTaking = frequencyOfTaking;
    }

    public String getMedicineMoreInfo() {
        return medicineMoreInfo;
    }

    public void setMedicineMoreInfo(String medicineMoreInfo) {
        this.medicineMoreInfo = medicineMoreInfo;
    }

    public int getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(int medicineImage) {
        this.medicineImage = medicineImage;
    }
}
