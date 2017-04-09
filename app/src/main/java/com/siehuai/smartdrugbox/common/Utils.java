package com.siehuai.smartdrugbox.common;

import java.util.ArrayList;

public class Utils {


    public static String convertListToString(ArrayList<String> list) {
        StringBuilder strBuffer = new StringBuilder();
        for (String msg : list) {
            strBuffer.append(msg + "\n");
        }
        return strBuffer.toString();
    }

    public static Double safeParseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1.00;
        }
    }

    public static Integer safeParseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
