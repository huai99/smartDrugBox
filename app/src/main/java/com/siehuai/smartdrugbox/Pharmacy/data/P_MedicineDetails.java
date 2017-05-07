package com.siehuai.smartdrugbox.Pharmacy.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class P_MedicineDetails implements IDbData, Parcelable {

    private String id;
    private String medicineName;
    private double price;
    private String description;
    private int frequencyOfTaking;
    private String medicineMoreInfo;
    private String medicineImage;
    private boolean showStatus;

    public P_MedicineDetails() {
    }

    public P_MedicineDetails(String medicineName,
                             double price,
                             String description,
                             int frequencyOfTaking,
                             String medicineMoreInfo,
                             String medicineImage,
                             boolean showStatus
                             ) {
        this.medicineName = medicineName;
        this.price = price;
        this.description = description;
        this.frequencyOfTaking = frequencyOfTaking;
        this.medicineMoreInfo = medicineMoreInfo;
        this.medicineImage = medicineImage;
        this.showStatus = showStatus;
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

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    protected P_MedicineDetails(Parcel in) {
        id = in.readString();
        medicineName = in.readString();
        price = in.readDouble();
        description = in.readString();
        frequencyOfTaking = in.readInt();
        medicineMoreInfo = in.readString();
        medicineImage = in.readString();
        showStatus = in.readInt() == 0 ? false : true;
    }

    public static final Creator<P_MedicineDetails> CREATOR = new Creator<P_MedicineDetails>() {
        @Override
        public P_MedicineDetails createFromParcel(Parcel in) {
            return new P_MedicineDetails(in);
        }

        @Override
        public P_MedicineDetails[] newArray(int size) {
            return new P_MedicineDetails[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(medicineName);
        dest.writeDouble(price);
        dest.writeString(description);
        dest.writeInt(frequencyOfTaking);
        dest.writeString(medicineMoreInfo);
        dest.writeString(medicineImage);
        dest.writeInt(showStatus ? 1 : 0);
    }

}
