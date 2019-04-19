package com.example.LBGManager.Model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String memeber_id;
    private String name;
    private Responsability responsability = null;
    private boolean administrator = false;

    public Member(String username, String member_id) {
        this.name = username;
        this.memeber_id = member_id;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public void setResponsability(Responsability responsability) {
        this.responsability = responsability;
    }

    public String getMemeber_id() {
        return memeber_id;
    }
    

    public String getName() {
        return name;
    }
}
