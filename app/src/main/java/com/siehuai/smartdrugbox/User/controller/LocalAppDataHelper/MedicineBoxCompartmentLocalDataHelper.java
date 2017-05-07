package com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper;

import android.util.Log;

import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.ILocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class MedicineBoxCompartmentLocalDataHelper extends Observable implements ILocalAppDataHelper {

    private ArrayList<IDbData> mMedicineBoxCompartmentList = new ArrayList<>();

    private static MedicineBoxCompartmentLocalDataHelper instance;
    private static Object lock = new Object();

    @Override
    public void insert(Object obj) {

    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void read(Iterator<?> iterator) {
        mMedicineBoxCompartmentList.clear();
        while (iterator.hasNext()) {
            IDbData value = (MedicineBoxDetails) iterator.next();
            String key = value.getId();
            mMedicineBoxCompartmentList.add(value);
            Log.d("Medicine Box Details", value.toString());
        }
        setChanged();
        notifyObservers();
    }

    public static MedicineBoxCompartmentLocalDataHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MedicineBoxCompartmentLocalDataHelper();
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
        return mMedicineBoxCompartmentList;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }
}
