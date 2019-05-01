package com.example.bahaa.chatapp.Root;

public class MessageModel {

    private String senderName;

    private String body;

    public MessageModel() {
        //Required empty public constructor
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
