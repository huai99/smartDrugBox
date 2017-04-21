package com.siehuai.smartdrugbox.Pharmacy.controller.LocalAppDataHelper;

import android.graphics.Bitmap;
import android.util.Log;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.ILocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.Pharmacy.data.P_MedicineDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


public class P_MedicineDetailsLocalDataHelper extends Observable implements ILocalAppDataHelper {

    private ArrayList<P_MedicineDetails> mMedicineDetailList = new ArrayList<>();
    private Map<String, P_MedicineDetails> mMedicineDetailMap = new HashMap<>();

    private static P_MedicineDetailsLocalDataHelper instance;
    private static Object lock = new Object();

    private P_MedicineDetailsLocalDataHelper() {
    }

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
        mMedicineDetailList.clear();
        while (iterator.hasNext()) {
            P_MedicineDetails value = (P_MedicineDetails) iterator.next();
            String key = value.getId();
            mMedicineDetailMap.put(key, value);
            mMedicineDetailList.add(value);
            Log.d("P_Medicine", value.toString());
        }
        setChanged();
        notifyObservers();
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

    public Object returnMenuTextList() {
        ArrayList<String> textList = new ArrayList<>();
        for (P_MedicineDetails medicineDetails : mMedicineDetailList) {
            textList.add(medicineDetails.getMedicineName());
        }
        return textList;
    }

    public Object returnMenuImgList() {
        ArrayList<Bitmap> bitMapList = new ArrayList<>();
        for (P_MedicineDetails medicineDetails : mMedicineDetailList) {
            bitMapList.add(Utils.Base64toBitMap(medicineDetails.getMedicineImage()));
        }
        return bitMapList;
    }

    public Map<String, IDbData> returnMenuMap() {
        Map<String, IDbData> menuMap = new HashMap<>();
        for(P_MedicineDetails medicineDetails:mMedicineDetailList){
            menuMap.put(medicineDetails.getId(),medicineDetails);
        }
        return menuMap;
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
