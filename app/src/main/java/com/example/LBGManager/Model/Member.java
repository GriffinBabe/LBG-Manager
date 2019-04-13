package com.example.LBGManager.Model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String memeber_id;
    private String name;
    private List<Responsability> responsabilities;
    private boolean administrator = false;

    public Member(String username, String member_id) {
        this.responsabilities = new ArrayList<>();
        this.name = username;
        this.memeber_id = member_id;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getMemeber_id() {
        return memeber_id;
    }

    public void addReponsabilities(Responsability responsability) {
        responsabilities.add(responsability);
    }

    public List<Responsability> getResponsabilities() {
        return this.responsabilities;
    }

    public String getName() {
        return name;
    }
}
