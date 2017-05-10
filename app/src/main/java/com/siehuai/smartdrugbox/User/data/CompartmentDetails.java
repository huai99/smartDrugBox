package com.siehuai.smartdrugbox.User.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

public class CompartmentDetails implements IDbData, Parcelable {

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

    protected CompartmentDetails(Parcel in) {
        id = in.readString();
        medicineBoxId = in.readString();
        runOutAlert = in.readInt() != 0;
        fillUpStatus = in.readInt() != 0;
        mMedicineDetails = in.readParcelable(mMedicineDetails.getClass().getClassLoader());
    }

    public static final Creator<CompartmentDetails> CREATOR = new Creator<CompartmentDetails>() {
        @Override
        public CompartmentDetails createFromParcel(Parcel in) {
            return new CompartmentDetails(in);
        }

        @Override
        public CompartmentDetails[] newArray(int size) {
            return new CompartmentDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(medicineBoxId);
        dest.writeInt(runOutAlert ? 1 : 0);
        dest.writeInt(fillUpStatus ? 1 : 0);
        dest.writeParcelable(mMedicineDetails,flags);
    }

}
