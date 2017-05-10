package com.siehuai.smartdrugbox.User.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;
import com.siehuai.smartdrugbox.Generic.data.IDbData;

@IgnoreExtraProperties
public class MedicineDetails implements IDbData, Parcelable {

    private String id;
    private String medicineName;
    private String drugstore;
    private String description;
    private String frequencyOfTaking;
    private String medicineImg;

    public MedicineDetails() {
    }

    public MedicineDetails(String id,
                           String medicineName,
                           String drugstore,
                           String description,
                           String frequencyOfTaking,
                           String medicineImg) {
        this.id = id;
        this.medicineName = medicineName;
        this.drugstore = drugstore;
        this.description = description;
        this.frequencyOfTaking = frequencyOfTaking;
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

    public String getMedicineImg() {
        return medicineImg;
    }

    public void setMedicineImg(String medicineImg) {
        this.medicineImg = medicineImg;
    }

    protected MedicineDetails(Parcel in) {
        id = in.readString();
        medicineName = in.readString();
        drugstore = in.readString();
        description = in.readString();
        frequencyOfTaking = in.readString();
        medicineImg = in.readString();
    }

    public static final Creator<MedicineDetails> CREATOR = new Creator<MedicineDetails>() {
        @Override
        public MedicineDetails createFromParcel(Parcel in) {
            return new MedicineDetails(in);
        }

        @Override
        public MedicineDetails[] newArray(int size) {
            return new MedicineDetails[size];
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
        dest.writeString(drugstore);
        dest.writeString(description);
        dest.writeString(frequencyOfTaking);
    }

}
