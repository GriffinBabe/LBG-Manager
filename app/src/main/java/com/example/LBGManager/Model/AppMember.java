package com.example.LBGManager.Model;

public class AppMember {

    private static Member member;
    private static String member_id = "";
    private static String connection_id;
    private static String password;
    private static AppMember APPMEMBER;


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

    public static void setConnection_id(String connection_id) {
        AppMember.connection_id = connection_id;
    }

    public static void setPassword(String password) {
        AppMember.password = password;
    }

    public static String getConnection_id() {
        return connection_id;
    }

    public static String getPassword() {
        return password;
    }

    public static Member getMember() {
        return member;
    }

    public static void setMember(Member member) {
        AppMember.member = member;
    }

    public static String getMember_id() {
        return member_id;
    }

    public static void setMember_id(String member_id) {
        AppMember.member_id = member_id;
    }
}
