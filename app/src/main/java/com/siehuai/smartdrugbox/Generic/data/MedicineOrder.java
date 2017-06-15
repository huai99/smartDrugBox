package com.siehuai.smartdrugbox.Generic.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.siehuai.smartdrugbox.User.data.MedicineDetails;

public class MedicineOrder implements IDbData, Parcelable {

    String id;
    String userName;
    String address;
    String contact;
    MedicineDetails medicineDetails;
    boolean availability;
    PharmacyDetails pharmacyDetails;
    boolean targetSinglePharmacy;

    public MedicineOrder() {
    }

    public MedicineOrder(String id,
                         String userName,
                         String address,
                         String contact,
                         MedicineDetails medicineDetails,
                         boolean availability,
                         PharmacyDetails pharmacyDetails,
                         boolean targetSinglePharmacy) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.contact = contact;
        this.medicineDetails = medicineDetails;
        this.availability = availability;
        this.pharmacyDetails = pharmacyDetails;
        this.targetSinglePharmacy = targetSinglePharmacy;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public MedicineDetails getMedicineDetails() {
        return medicineDetails;
    }

    public void setMedicineDetails(MedicineDetails medicineDetails) {
        this.medicineDetails = medicineDetails;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public PharmacyDetails getPharmacyDetails() {
        return pharmacyDetails;
    }

    public void setPharmacyDetails(PharmacyDetails pharmacyDetails) {
        this.pharmacyDetails = pharmacyDetails;
    }

    public boolean isTargetSinglePharmacy() {
        return targetSinglePharmacy;
    }

    public void setTargetSinglePharmacy(boolean targetSinglePharmacy) {
        this.targetSinglePharmacy = targetSinglePharmacy;
    }

    protected MedicineOrder(Parcel in) {
        id = in.readString();
        userName = in.readString();
        address = in.readString();
        contact = in.readString();
        medicineDetails = in.readParcelable(MedicineDetails.class.getClassLoader());
        availability = in.readInt() != 0;
        pharmacyDetails = in.readParcelable(PharmacyDetails.class.getClassLoader());
        targetSinglePharmacy = in.readInt() != 0;
    }

    public static final Parcelable.Creator<MedicineOrder> CREATOR = new Parcelable.Creator<MedicineOrder>() {
        @Override
        public MedicineOrder createFromParcel(Parcel in) {
            return new MedicineOrder(in);
        }

        @Override
        public MedicineOrder[] newArray(int size) {
            return new MedicineOrder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userName);
        dest.writeString(address);
        dest.writeString(contact);
        dest.writeParcelable(medicineDetails, flags);
        dest.writeInt(availability ? 1 : 0);
        dest.writeParcelable(pharmacyDetails, flags);
        dest.writeInt(targetSinglePharmacy ? 1 : 0);

    }
}
