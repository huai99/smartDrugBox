package com.siehuai.smartdrugbox.Pharmacy.data.MenuResource;

import android.graphics.Bitmap;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.MenuResource;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class P_MedicineDetailsMenuResourceTesting {

    ArrayList<P_MedicineDetails> testResourceList = new ArrayList<P_MedicineDetails>() {
        {
            add(new P_MedicineDetails());
            add(new P_MedicineDetails());
            add(new P_MedicineDetails());
        }
    };
    MenuResource menuResource;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Utils.class);
        Bitmap bitmap = PowerMockito.mock(Bitmap.class);
        Byte testByte = PowerMockito.mock(Byte.class);
        byte[] testBytes = new byte[]{testByte};
        PowerMockito.when(Utils.Base64toBitMap(null)).thenReturn(bitmap);
        menuResource = new P_MedicineDetailsMenuResource();
        menuResource.setResourceList(testResourceList);
    }

    @Test
    public void testResourceImgListSizeSameAsResourceList() {
        menuResource.setResourceList(testResourceList);
        int imgListSize = menuResource.getResourceImgList().size();
        int resourceListSize = menuResource.getResourceList().size();
        Assert.assertEquals("ImageList size should be the same as resource list size", imgListSize, resourceListSize);
    }

    @Test
    public void testResourceTextListSizeSameAsResourceList() {
        menuResource.setResourceList(testResourceList);
        int textListSize = menuResource.getResourceTextList().size();
        int resourceListSize = menuResource.getResourceList().size();
        Assert.assertEquals("TextList size should be the same as resource list size", 3, resourceListSize);
    }
}
