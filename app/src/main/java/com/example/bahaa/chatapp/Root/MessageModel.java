package com.example.bahaa.chatapp.Root;

public class MessageModel {

    private String messageSenderName;

    private String messageBody;

    public MessageModel() {
        //Required empty public constructor
    }

    public String getMessageSenderName() {
        return messageSenderName;
    }

    public void setMessageSenderName(String messageSenderName) {
        this.messageSenderName = messageSenderName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
