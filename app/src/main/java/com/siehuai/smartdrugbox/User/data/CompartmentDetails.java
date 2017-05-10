package com.siehuai.smartdrugbox.User.data;

public class CompartmentDetails {

    boolean runOutAlert;
    boolean fillUpStatus;
    MedicineDetails mMedicineDetails;
    String id;
    String medicineBoxId;

    public CompartmentDetails() {
    }

    public CompartmentDetails(String id, String medicineBoxId, boolean runOutAlert, boolean fillUpStatus, MedicineDetails medicineDetails) {
        this.id = id;
        this.medicineBoxId = medicineBoxId;
        this.runOutAlert = runOutAlert;
        this.fillUpStatus = fillUpStatus;
        mMedicineDetails = medicineDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineBoxId() {
        return medicineBoxId;
    }

    public void setMedicineBoxId(String medicineBoxId) {
        this.medicineBoxId = medicineBoxId;
    }

    public boolean isRunOutAlert() {
        return runOutAlert;
    }

    public void setRunOutAlert(boolean runOutAlert) {
        this.runOutAlert = runOutAlert;
    }

    public boolean isFillUpStatus() {
        return fillUpStatus;
    }

    public void setFillUpStatus(boolean fillUpStatus) {
        this.fillUpStatus = fillUpStatus;
    }

    public MedicineDetails getMedicineDetails() {
        return mMedicineDetails;
    }

    public void setMedicineDetails(MedicineDetails medicineDetails) {
        mMedicineDetails = medicineDetails;
    }
}
