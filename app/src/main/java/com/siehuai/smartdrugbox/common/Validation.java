package com.siehuai.smartdrugbox.common;

import android.util.Log;

public class Validation {

    public static boolean isPositiveInteger(int input) {
        return input >= 0;
    }

    public static String returnIsPositiveErrorMsg() {
        return "Input must be positive integer";
    }

    public static boolean isInBetweenTwoInteger(int targetNum, int smallerNum, int biggerNum) {
        boolean result = (targetNum > smallerNum) && (targetNum < biggerNum);
        Log.d("isInBetween",String.valueOf(result));
        return result;
    }

    public static String returnIsInBetweenErrorMsg(int smallerNum, int biggerNum) {
        return "Input must between " + String.valueOf(smallerNum) + " and " + String.valueOf(biggerNum);
    }
}
