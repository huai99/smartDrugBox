package com.siehuai.smartdrugbox.User.data;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class MedicineBoxDetails implements IDbData {

    private String id;
    private String userName;
    private String userImg;
    private int totalSlotNumber;
    private int fillNumber;
    private String emergencyContact;
    private String patientAddress;

    public MedicineBoxDetails() {
    }

    public MedicineBoxDetails(String id, String userName, String userImg, int totalSlotNumber, int fillNumber, String emergencyContact, String patientAddress) {
        this.id = id;
        this.userName = userName;
        this.userImg = userImg;
        this.totalSlotNumber = totalSlotNumber;
        this.fillNumber = fillNumber;
        this.emergencyContact = emergencyContact;
        this.patientAddress = patientAddress;
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

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getTotalSlotNumber() {
        return totalSlotNumber;
    }

    public void setTotalSlotNumber(int totalSlotNumber) {
        this.totalSlotNumber = totalSlotNumber;
    }

    public int getFillNumber() {
        return fillNumber;
    }

    public void setFillNumber(int fillNumber) {
        this.fillNumber = fillNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }
}
