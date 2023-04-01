package com.termproject;

public class Main {
    private Trip ActiveTrip;
    private PackageList Packages;
    private PersonList People;
    private AgentList Agents;

    private static String LoadConfigFile() {
        System.out.println("Loading config...");
        return "json";
    }

    public static void main(String[] args) {
        String format = LoadConfigFile();
        System.out.println("Loaded config: " + format);
    }
}