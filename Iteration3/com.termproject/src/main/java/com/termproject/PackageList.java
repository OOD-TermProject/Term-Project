package com.termproject;

public class PackageList {
    private static PackageList uniqueInstance = new PackageList();

    private PackageList() {
        // Load the package list in here
    }

    public static PackageList getInstance() {
        return uniqueInstance;
    }
}
