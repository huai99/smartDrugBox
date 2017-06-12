package com.siehuai.smartdrugbox.Pharmacy.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class PharmacyDetails implements IDbData, Parcelable {

    private String id;
    private String pharmacyImg;
    private String pharmacyName;
    private String ownerName;
    private String address;
    private String openingTime;
    private String closingTime;
    private String contactNumber;
    private String email;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getPharmacyImg() {
        return pharmacyImg;
    }

    public void setPharmacyImg(String pharmacyImg) {
        this.pharmacyImg = pharmacyImg;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private PharmacyDetails(Parcel in) {
        id = in.readString();
        pharmacyImg = in.readString();
        pharmacyName = in.readString();
        ownerName = in.readString();
        address = in.readString();
        openingTime = in.readString();
        closingTime = in.readString();
        contactNumber = in.readString();
        email = in.readString();
    }

    public static final Parcelable.Creator<PharmacyDetails> CREATOR = new Parcelable.Creator<PharmacyDetails>() {
        @Override
        public PharmacyDetails createFromParcel(Parcel in) {
            return new PharmacyDetails(in);
        }

        @Override
        public PharmacyDetails[] newArray(int size) {
            return new PharmacyDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(pharmacyImg);
        dest.writeString(pharmacyName);
        dest.writeString(ownerName);
        dest.writeString(address);
        dest.writeString(openingTime);
        dest.writeString(closingTime);
        dest.writeString(contactNumber);
        dest.writeString(email);
    }
}
