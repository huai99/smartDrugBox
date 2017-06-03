package com.siehuai.smartdrugbox.Generic.data;

import com.siehuai.smartdrugbox.User.data.MedicineDetails;

public class MedicineOrder implements IDbData {

    String id;
    String userName;
    String address;
    String contact;
    MedicineDetails medicineDetails;
    boolean availability;

    public MedicineOrder() {
    }

    public MedicineOrder(String id, String userName, String address, String contact, MedicineDetails medicineDetails, boolean availability) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.contact = contact;
        this.medicineDetails = medicineDetails;
        this.availability = availability;
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
}
