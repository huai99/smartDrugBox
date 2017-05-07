package com.siehuai.smartdrugbox.Generic.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UtilsTest {

    @Test
    public void convertListToStringTest() {
        ArrayList<String> list = new ArrayList<String>() {
            {
                add("Test1");
                add("Test2");
            }
        };
        String sample = "Test1\nTest2\n";
        String result = Utils.convertListToString(list);
        Assert.assertEquals("Both of the String shall be equal", result, sample);
    }

    @Test
    public void safeParseDoubleTest() {
        String sample1 = "1.00";
        String sample2 = "Wrong Data";

        Assert.assertEquals("Both should be equal", (Double) 1.00, Utils.safeParseDouble(sample1), 1e-8);
        Assert.assertEquals("Both should be equal", -1.00, Utils.safeParseDouble(sample2), 1e-8);
    }

    @Test
    public void safeParseIntegerTest() {
        String sample1 = "1";
        String sample2 = "Wrong Data";

        Assert.assertEquals("Both should be equal", 1, (int) Utils.safeParseInteger(sample1));
        Assert.assertEquals("Both should be equal", -1, (int) Utils.safeParseInteger(sample2));
    }
}
