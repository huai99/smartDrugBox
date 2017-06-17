package com.siehuai.smartdrugbox.Generic.data.ListResource.ListResourceImpl;

import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;
import com.siehuai.smartdrugbox.Generic.data.Message;
import com.siehuai.smartdrugbox.Pharmacy.data.ListResource.ListResourceFieldConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageQueueListResource implements IListResource {

    private ArrayList<Message> messageList;
    private Map<String, ArrayList> mListMap = new HashMap<>();

    @Override
    public int getResourceSize() {
        return messageList.size();
    }

    @Override
    public Map<String, ArrayList> getResourceTextMap() {
        return mListMap;
    }

    @Override
    public void setResourceTextMap() {
        ArrayList<String> timeStampList = new ArrayList<>();
        ArrayList<String> senderList = new ArrayList<>();
        ArrayList<String> messageTitleList = new ArrayList<>();
        ArrayList<String> priorityList = new ArrayList<>();
        for (Message message : messageList) {
            timeStampList.add(message.getTime());
            senderList.add(message.getSender());
            messageTitleList.add(message.getTitle());
            priorityList.add(message.getPriority());
        }
        mListMap.put(ListResourceFieldConst.COLUMN1, timeStampList);
        mListMap.put(ListResourceFieldConst.COLUMN2, senderList);
        mListMap.put(ListResourceFieldConst.COLUMN3, messageTitleList);
        mListMap.put(ListResourceFieldConst.COLUMN4, priorityList);
    }

    @Override
    public ArrayList<?> getResourceList() {
        return messageList;
    }

    @Override
    public void setResourceList(ArrayList<?> resourceList) {
        messageList = (ArrayList<Message>) resourceList;
        setResourceTextMap();
    }
}
