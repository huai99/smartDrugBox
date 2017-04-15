package com.siehuai.smartdrugbox.Pharmacy.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.siehuai.smartdrugbox.Generic.data.IRemoteDbData;

public class P_MedicineDetails implements IRemoteDbData,Parcelable {

    private String id;
    private String medicineName;
    private double price;
    private String description;
    private int frequencyOfTaking;
    private String medicineMoreInfo;
    private String medicineImage;

    public P_MedicineDetails() {
    }

    public P_MedicineDetails(String medicineName,
                             double price,
                             String description,
                             int frequencyOfTaking,
                             String medicineMoreInfo,
                             String medicineImage) {
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

    public String getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(String medicineImage) {
        this.medicineImage = medicineImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this,flags);
    }
}
