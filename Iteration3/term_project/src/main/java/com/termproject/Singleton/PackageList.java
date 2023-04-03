package com.termproject.Singleton;

import com.google.gson.Gson;
import com.termproject.Trip.Package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class PackageList {
    private static final PackageList uniqueInstance = new PackageList();
    private static final String packagesFile = "term_project/src/main/java/com/termproject/Trip/packages.json";
    ArrayList<Package> packageList;

    private PackageList() {
        loadPackagesFile();
    }

    private void loadPackagesFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(packagesFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();

        packageList = gson.fromJson(reader, ArrayList.class);
    }

    public static PackageList getInstance() {
        return uniqueInstance;
    }

    public ArrayList<Package> getPackageList() {
        return packageList;
    }
}
