package com.example.chatapp.firebase;

public class Chat {
    private String senderId;
    private String receiverId;
    private String massage;
    private long sendingTime;

    public  Chat()
    {
        senderId="";
        receiverId="";
        massage="";
        sendingTime=0;
    }

    public Chat(String senderId, String reciverId, String massage, long sendingTime) {
        this.senderId = senderId;
        this.receiverId = reciverId;
        this.massage = massage;
        this.sendingTime = sendingTime;
    }

    public boolean isSenderMessage(String pId)
    {
        if(senderId.equals(pId))
            return true;
        else
            return false;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String reciverId) {
        this.receiverId = reciverId;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public long getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(long sendingTime) {
        this.sendingTime = sendingTime;
    }


}
