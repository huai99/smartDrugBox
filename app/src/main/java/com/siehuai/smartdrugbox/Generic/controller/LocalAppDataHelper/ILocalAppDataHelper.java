package com.siehuai.smartdrugbox.Generic.controller.LocalAppDataHelper;

import java.util.Iterator;

public interface ILocalAppDataHelper {

    void insert(Object obj);

    void delete(Object obj);

    void update(Object obj);

    void read(Iterator<?> iterator);

    Object returnAppData();
}
