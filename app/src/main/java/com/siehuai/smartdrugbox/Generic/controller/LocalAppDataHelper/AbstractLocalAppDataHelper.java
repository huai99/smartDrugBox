package com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public abstract class AbstractLocalAppDataHelper extends Observable implements ILocalAppDataHelper {

    private static AbstractLocalAppDataHelper instance;
    private static Object lock = new Object();
    protected Map<String, IDbData> dataMap = new HashMap<>();

    public void findAll(final IDbOnDataChangeListener listener) {
        final Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                listener.onDataChange(arg);
            }
        };
        addObserver(observer);
        setChanged();
        notifyObservers(dataMap.values());
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
}
