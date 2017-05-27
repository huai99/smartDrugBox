package com.siehuai.smartdrugbox.Pharmacy.controller.LocalAppDataHelper;

import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.AbstractLocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.Iterator;
import java.util.Map;


public class P_MedicineDetailsLocalDataHelper extends AbstractLocalAppDataHelper {

    private Map<String, IDbData> mMedicineDetailsMap = dataMap;

    private static P_MedicineDetailsLocalDataHelper instance;
    private static Object lock = new Object();

    private P_MedicineDetailsLocalDataHelper() {
    }

    @Override
    public void read(Iterator<?> iterator) {
        mMedicineDetailsMap.clear();
        while (iterator.hasNext()) {
            P_MedicineDetails value = (P_MedicineDetails) iterator.next();
            String key = value.getId();
            mMedicineDetailsMap.put(key, value);
            Log.d("P_Medicine", value.toString());
        }
        setChanged();
        notifyObservers(mMedicineDetailsMap.values());
    }

    public static P_MedicineDetailsLocalDataHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new P_MedicineDetailsLocalDataHelper();
                    return instance;
                } else {
                    return instance;
                }
            }
        } else {
            return instance;
        }
    }

    @Override
    public Object returnAppData() {
        return mMedicineDetailsMap;
    }
}
