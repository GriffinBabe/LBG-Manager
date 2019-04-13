package com.example.LBGManager.Model;

public enum Responsability {
    President("President"),
    Treasurer("Treasurer"),
    Secretary("Secretary"),
    VP_PR("Vice President in Public Relations"),
    VP_HR("Vice President in Human Resources"),
    VP_FR("Vice President in Foundraising");

    private String name = "";

    Responsability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
