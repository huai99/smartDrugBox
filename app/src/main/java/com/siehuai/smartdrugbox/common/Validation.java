package com.siehuai.smartdrugbox.common;

public class Validation {

    public static boolean isPositiveInteger(int input) {
        return input >= 0;
    }

    public static String returnIsPositiveErrorMsg(){
        return "Input must be positive integer";
    }
}
