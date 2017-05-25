package com.siehuai.smartdrugbox.Pharmacy.controller.LocalAppDataHelper;

import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.AbstractLocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;


public class P_MedicineDetailsLocalDataHelper extends AbstractLocalAppDataHelper {

    private ArrayList<IDbData> mMedicineDetailList = dataList;

    private static P_MedicineDetailsLocalDataHelper instance;
    private static Object lock = new Object();

    @Override
    public void read(Iterator<?> iterator) {
        mMedicineDetailList.clear();
        while (iterator.hasNext()) {
            P_MedicineDetails value = (P_MedicineDetails) iterator.next();
            String key = value.getId();
            mMedicineDetailList.add(value);
            Log.d("P_Medicine", value.toString());
        }
        setChanged();
        notifyObservers(mMedicineDetailList);
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
        return mMedicineDetailList;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
