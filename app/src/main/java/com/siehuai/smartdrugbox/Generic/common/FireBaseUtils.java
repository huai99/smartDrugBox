package com.siehuai.smartdrugbox.Generic.common;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class FireBaseUtils {

    public static <E> Iterator<E> convertDataSnapshotIterator(Iterator<DataSnapshot> iterator, Class<E> clazz) {
        ArrayList<E> arrayList = new ArrayList<E>();
        while (iterator.hasNext()) {
            E object = iterator.next().getValue(clazz);
            arrayList.add(object);
        }
        return arrayList.iterator();
    }

    public static Iterator SpecialConvertDataSnapshotIterator(Iterator<DataSnapshot> iterator) {
        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) iterator.next().getValue();
            arrayList.add(map);
        }
        return arrayList.iterator();
    }
}
