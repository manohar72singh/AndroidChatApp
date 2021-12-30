package com.example.chatapp.database;

public class User {
    private String name;
    private String uuid;
    private String emailId;
    private String mob;
    private String gender;



    public User()
    {
        name = "";
        uuid = "";
        emailId = "";
        mob = "";
        gender="";
    }

    public User(String name, String uuid, String emailId, String mob) {
        this.name = name;
        this.uuid = uuid;
        this.emailId = emailId;
        this.mob = mob;
    }

    public User(String name, String uuid, String emailId, String mob, String gender) {
        this.name = name;
        this.uuid = uuid;
        this.emailId = emailId;
        this.mob = mob;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
