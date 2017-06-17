package com.siehuai.smartdrugbox.Pharmacy.data.ListResource;

import com.siehuai.smartdrugbox.Generic.data.MedicineOrder;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class P_OrderQueueListResource implements IListResource {

    private ArrayList<MedicineOrder> medicineOrderList;
    private Map<String, ArrayList> mListMap = new HashMap<>();

    @Override
    public int getResourceSize() {
        return medicineOrderList.size();
    }

    @Override
    public Map<String, ArrayList> getResourceTextMap() {
        return mListMap;
    }


    @Override
    public void setResourceTextMap() {
        ArrayList<String> userNameList = new ArrayList<>();
        ArrayList<String> contactList = new ArrayList<>();
        ArrayList<String> medicineList = new ArrayList<>();
        ArrayList<String> completionStatusList = new ArrayList<>();
        for (MedicineOrder medicineOrder : medicineOrderList) {
            userNameList.add(medicineOrder.getUserName());
            contactList.add(medicineOrder.getContact());
            medicineList.add(medicineOrder.getMedicineDetails().getMedicineName());
            completionStatusList.add(String.valueOf(medicineOrder.isCompletionStatus()));
        }
        mListMap.put(ListResourceFieldConst.COLUMN1, userNameList);
        mListMap.put(ListResourceFieldConst.COLUMN2, contactList);
        mListMap.put(ListResourceFieldConst.COLUMN3, medicineList);
        mListMap.put(ListResourceFieldConst.COLUMN4, completionStatusList);
    }

    @Override
    public ArrayList<?> getResourceList() {
        return medicineOrderList;
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        medicineOrderList = (ArrayList<MedicineOrder>) resourceList;
        setResourceTextMap();
    }
}
