package com.siehuai.smartdrugbox.Generic.common;

import android.util.Log;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class ValidationTest {

    @Before
    public void setup() {
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    public void isPositiveIntegerTest() {
        Assert.assertTrue(Validation.isPositiveInteger(1));
        Assert.assertTrue(Validation.isPositiveInteger(2));
        Assert.assertTrue(Validation.isPositiveInteger(3));
        Assert.assertFalse(Validation.isPositiveInteger(-1));
        Assert.assertFalse(Validation.isPositiveInteger(-2));
        Assert.assertFalse(Validation.isPositiveInteger(-3));
    }

    @Test
    public void isInBetweenTwoIntegerTest() {
        Assert.assertTrue("Number should be in between:", Validation.isInBetweenTwoInteger(6, 2, 8));
        Assert.assertTrue("Number should be in between:", Validation.isInBetweenTwoInteger(10, 9, 11));
        Assert.assertTrue("Number should be in between:", Validation.isInBetweenTwoInteger(-10, -11, -9));
        Assert.assertFalse("Number should not in between:", Validation.isInBetweenTwoInteger(-10, -14, -11));
        Assert.assertFalse("Number should not in between:", Validation.isInBetweenTwoInteger(10, 2, 7));
        Assert.assertFalse("Smaller Num and Bigger Num is inverted", Validation.isInBetweenTwoInteger(10, 12, 7));
    }
}
