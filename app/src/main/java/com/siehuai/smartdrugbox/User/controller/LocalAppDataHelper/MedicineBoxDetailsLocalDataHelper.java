package com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper;

import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.AbstractLocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

public class MedicineBoxDetailsLocalDataHelper extends AbstractLocalAppDataHelper {

    private ArrayList<IDbData> mMedicineBoxDetailList = dataList;

    private static MedicineBoxDetailsLocalDataHelper instance;
    private static Object lock = new Object();

    @Override
    public void read(Iterator<?> iterator) {
        mMedicineBoxDetailList.clear();
        while (iterator.hasNext()) {
            IDbData value = (MedicineBoxDetails) iterator.next();
            String key = value.getId();
            mMedicineBoxDetailList.add(value);
            Log.d("Medicine Box Details", value.toString());
        }
        setChanged();
        notifyObservers(mMedicineBoxDetailList);
    }

    @Override
    public Object returnAppData() {
        return mMedicineBoxDetailList;
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

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
