package com.siehuai.smartdrugbox.Generic.common;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Iterator;

public class FireBaseUtils {

    public static <E> Iterator<E> convertDataSnapshotIterator(Iterator<DataSnapshot> iterator, Class<E> clazz) {
        ArrayList<E> arrayList = new ArrayList<E>();
        while (iterator.hasNext()) {
            E object = iterator.next().getValue(clazz);
            arrayList.add(object);
        }
        return arrayList.iterator();
    }
}
