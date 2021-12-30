package com.example.chatapp.firebase;

public class ChatId {
    private String p1Id;  //participant1ID
    private String p2Id;  //participant2ID
    long creationDate;

    public ChatId() {
        p1Id = "";
        p2Id = "";
        creationDate=0;
    }
    public ChatId(String p1Id,String p2Id,long creationDate)
    {
        this.p1Id=p1Id;
        this.p2Id=p2Id;
        this.creationDate=creationDate;
    }

    public String getP1Id() {
        return p1Id;
    }

    public String getP2Id() {
        return p2Id;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setP1Id(String p1Id) {
        this.p1Id = p1Id;
    }

    public void setP2Id(String p2Id) {
        this.p2Id = p2Id;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public boolean match(String p1Id,String p2Id)
    {
        if((this.p1Id.equals(p1Id)&&this.p2Id.equals(p2Id))||(this.p1Id.equals(p2Id)&&this.p2Id.equals(p1Id)))
            return true;
        else
            return false;
    }

}
