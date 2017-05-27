package com.siehuai.smartdrugbox.User.controller.LocalAppDataHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.AbstractLocalAppDataHelper;
import com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper.IDbOnDataChangeListener;
import com.siehuai.smartdrugbox.Generic.data.IDbData;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;
import com.siehuai.smartdrugbox.User.view.UserUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MedicineBoxCompartmentLocalDataHelper extends AbstractLocalAppDataHelper {

    private ObjectMapper mObjectMapper = new ObjectMapper();

    private Map<String, IDbData> mMedicineBoxCompartmentMap = dataMap;

    private static MedicineBoxCompartmentLocalDataHelper instance;
    private static Object lock = new Object();

    private MedicineBoxCompartmentLocalDataHelper() {
    }

    @Override
    public void read(Iterator<?> iterator) {
        mMedicineBoxCompartmentMap.clear();
        while (iterator.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            MedicineBoxCompartment medicineBoxCompartment
                    = UserUtils.convertRawToMedicineCompartment(mObjectMapper, map);
            mMedicineBoxCompartmentMap.put(medicineBoxCompartment.getId(), medicineBoxCompartment);
        }
        setChanged();
        notifyObservers(mMedicineBoxCompartmentMap.values());
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

    public MedicineBoxCompartment findOneFromRemote(Iterator<?> iterator) {
        MedicineBoxCompartment medicineBoxCompartment = null;
        while (iterator.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            medicineBoxCompartment
                    = UserUtils.convertRawToMedicineCompartment(mObjectMapper, map);
        }
        return medicineBoxCompartment;
    }

    public void find(final String id, final IDbOnDataChangeListener dataChangeListener) {
        Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                dataChangeListener.onDataChange(mMedicineBoxCompartmentMap.get(id));
            }
        };
        addObserver(observer);
        setChanged();
        notifyObservers();
    }

    @Override
    public Object returnAppData() {
        return mMedicineBoxCompartmentMap.values();
    }
}
