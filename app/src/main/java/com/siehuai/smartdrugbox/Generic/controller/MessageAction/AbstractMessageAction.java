package com.siehuai.smartdrugbox.Generic.controller.MessageAction;

public abstract class AbstractMessageAction implements IMessageAction {
    private String messageBody;
    private String messageTitle;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }
}
