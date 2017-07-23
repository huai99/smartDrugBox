package com.siehuai.smartdrugbox.Generic.data.ListResource.ListResourceImpl;

import com.siehuai.smartdrugbox.Generic.common.Utils;
import com.siehuai.smartdrugbox.Generic.data.MenuResource.IListResource;
import com.siehuai.smartdrugbox.Generic.data.Message;
import com.siehuai.smartdrugbox.Pharmacy.data.ListResource.ListResourceFieldConst;
import com.siehuai.smartdrugbox.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MessageQueueListResource implements IListResource {

    private ArrayList<Message> messageList;
    private Map<String, ArrayList> mListMap = new HashMap<>();
    private ArrayList<Integer> mColorList = new ArrayList<>();

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
            timeStampList.add(Utils.convertLongTimeToStringFormat(message.getTime(), "HH:mm:ss"));
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
        Collections.sort(messageList, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return (int)(o2.getTime() - o1.getTime()) ;
            }
        });
        setResourceTextMap();
        setColorList();
    }

    public void setColorList() {
        for (Message message : messageList) {
            if (message.isReadStatus()) {
                mColorList.add(R.color.grey_200);
            } else {
                mColorList.add(R.color.white);
            }
        }
    }

    @Override
    public ArrayList<?> getColorList() {
        return mColorList;
    }
}
