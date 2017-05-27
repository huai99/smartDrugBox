package com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper;

import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.AbstractLocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;

import java.util.Iterator;
import java.util.Map;

public class MedicineBoxDetailsLocalDataHelper extends AbstractLocalAppDataHelper {

    private Map<String, IDbData> mMedicineBoxDetailMap = dataMap;

    private static MedicineBoxDetailsLocalDataHelper instance;
    private static Object lock = new Object();

    private MedicineBoxDetailsLocalDataHelper() {
    }

    @Override
    public void read(Iterator<?> iterator) {
        mMedicineBoxDetailMap.clear();
        while (iterator.hasNext()) {
            IDbData value = (MedicineBoxDetails) iterator.next();
            String key = value.getId();
            mMedicineBoxDetailMap.put(key, value);
            Log.d("Medicine Box Details", value.toString());
        }
        setChanged();
        notifyObservers(mMedicineBoxDetailMap.values());
    }

    @Override
    public Object returnAppData() {
        return mMedicineBoxDetailMap.values();
    }

    public static MedicineBoxDetailsLocalDataHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MedicineBoxDetailsLocalDataHelper();
                    return instance;
                } else {
                    return instance;
                }
            }
        } else {
            return instance;
        }
    }
}
