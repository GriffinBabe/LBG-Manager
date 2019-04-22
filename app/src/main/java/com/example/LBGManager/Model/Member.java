package com.example.LBGManager.Model;

import java.io.Serializable;

public class Member implements Serializable {

    private String member_id;
    private String name;
    private Responsibility responsibility = null;
    private boolean administrator = false;

    public Member(String name, String member_id) {
        this.name = name;
        this.member_id = member_id;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public void setResponsibility(Responsibility responsibility) {
        this.responsibility = responsibility;
    }

    public String getMember_Id() {
        return member_id;
    }


    public String getName() {
        return name;
    }
}
