package com.siehuai.smartdrugbox.Generic.data;

public class FirebaseRegistrationToken implements IDbData {

    String registrationToken;

    public FirebaseRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getId() {
        return null;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }
}
