package com.siehuai.smartdrugbox.common;

import java.util.ArrayList;

public class Utils {
    public static String convertListToString(ArrayList<String> list) {
        StringBuffer strBuffer = new StringBuffer();
        for (String msg : list) {
            strBuffer.append(msg + "\n");
        }
        return strBuffer.toString();
    }
}
