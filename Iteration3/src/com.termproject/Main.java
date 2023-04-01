package com.termproject;

public class Main {
    private Trip ActiveTrip;
    private final PackageList Packages = PackageList.getInstance();
    private final PersonList People = PersonList.getInstance();
    private final AgentList Agents = AgentList.getInstance();

    private static String LoadConfigFile() {
        System.out.println("Loading config...");
        return "json";
    }

    public static void main(String[] args) {
        String format = LoadConfigFile();
        System.out.println("Loaded config: " + format);
    }
}