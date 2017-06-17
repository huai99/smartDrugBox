package com.siehuai.smartdrugbox.Generic.data;

public class Message implements IDbData {

    String id;
    String time;
    String title;
    String details;
    boolean readStatus;
    String sender;
    String priority;

    public Message() {
    }

    public Message(String id, String time, String title, String details, boolean readStatus, String sender, String priority) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.details = details;
        this.readStatus = readStatus;
        this.sender = sender;
        this.priority = priority;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
