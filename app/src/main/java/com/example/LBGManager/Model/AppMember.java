package com.example.LBGManager.Model;

import java.io.Serializable;

public class AppMember implements Serializable {

    private Member member;
    private String token;
    private static AppMember APPMEMBER = null;


    /**
     * AppMemeber is a singleton as only one user uses the app
     */
    private AppMember() {

    }

    public static AppMember getInstance() {
        if (APPMEMBER==null) {
            APPMEMBER = new AppMember();
        }
        return APPMEMBER;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
