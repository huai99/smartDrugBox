package com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper;

import android.util.Log;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.controller.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.ILocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.data.MedicineBoxDetails;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MedicineBoxDetailsLocalDataHelper extends Observable implements ILocalAppDataHelper {

    private ArrayList<IDbData> mMedicineBoxDetailList = new ArrayList<>();

    private static MedicineBoxDetailsLocalDataHelper instance;
    private static Object lock = new Object();

    private MedicineBoxDetailsLocalDataHelper() {
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

    public void findAll(final IDbOnDataChangeListener listener){
        final Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                listener.onDataChange(arg);
            }
        };
        addObserver(observer);
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
    public Object returnAppData() {
        return mMedicineBoxDetailList;
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

    private void specialHandleData(Iterator<?> iterator) {
        ArrayList<MedicineBoxDetails> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            MedicineBoxDetails boxDetails = new MedicineBoxDetails();
            String id = String.valueOf(map.get("id"));
            String userName = String.valueOf(map.get("userName"));
            String userImg = String.valueOf(map.get("userImg"));
            String emergencyContact = String.valueOf(map.get("emergencyContact"));
            String fillNumber = String.valueOf(map.get("fillNumber"));
            String totalSlotNumber = String.valueOf(map.get("totalSlotNumber"));
            Map<String, MedicineBoxDetails> boxDetailsMap = (Map<String, MedicineBoxDetails>) map.get("map");

            boxDetails.setId(id);
            boxDetails.setUserName(userName);
            boxDetails.setUserImg(userImg);
            boxDetails.setEmergencyContact(emergencyContact);
            boxDetails.setFillNumber(Utils.safeParseInteger(fillNumber));
            boxDetails.setTotalSlotNumber(Utils.safeParseInteger(totalSlotNumber));
        }
    }
}
