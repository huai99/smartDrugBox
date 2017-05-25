package com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper;

import com.siehuai.smartdrugbox.Generic.data.IDbData;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class AbstractLocalAppDataHelper extends Observable implements ILocalAppDataHelper {

    private static AbstractLocalAppDataHelper instance;
    private static Object lock = new Object();
    protected ArrayList<IDbData> dataList = new ArrayList<>();

    public void findAll(final IDbOnDataChangeListener listener) {
        final Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                listener.onDataChange(arg);
            }
        };
        addObserver(observer);
        setChanged();
        notifyObservers(dataList);
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
