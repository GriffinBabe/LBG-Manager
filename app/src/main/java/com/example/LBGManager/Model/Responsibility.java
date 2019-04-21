package com.example.LBGManager.Model;

public enum Responsibility {
    PR("President"),
    TR("Treasurer"),
    SEC("Secretary"),
    VP_PR("Vice President in Public Relations"),
    VP_HR("Vice President in Human Resources"),
    VP_FR("Vice President in Foundraising"),
    None("No official responsibility");

    private String name = "";

    Responsibility(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Responsibility getResponsibilityByName(String name) throws Exception {
        for (Responsibility responsibility : Responsibility.values()) {
            if (responsibility.equals(name)) {
                return responsibility;
            }
        }
        throw new Exception("Unknown responsibility value from name: "+name);
    }
}
