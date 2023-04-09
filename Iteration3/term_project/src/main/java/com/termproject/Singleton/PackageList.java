package com.termproject.Singleton;
import com.termproject.Transport.*;
import com.termproject.Trip.Package;
import com.termproject.Trip.Place;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**

 @file PackageList.java
 Represents a list of available travel packages
 */
public class PackageList {
    /**
     * Unique instance of the PackageList class.
     */
    private static final PackageList uniqueInstance = new PackageList();
    /**
    * Path to the packages file.
    */
    private static final String packagesFile = "term_project/src/main/java/com/termproject/Singleton/packages.txt";
    /**
     * List of travel packages
     */
    ArrayList<Package> packageList = new ArrayList<>();

    /**
     * Private constructor for the singleton instance of the class.
     */
    private PackageList() {
        loadPackagesFile();
    }

    /**
     * Loads information for each package object from the packages file and adds it to an ArrayList.
     */
    private void loadPackagesFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(packagesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String transportName = data[0];
                float price = Float.parseFloat(data[1]);
                int hoursOfTravelTime = Integer.parseInt(data[2]);
                Place travelsFrom = new Place(data[3]);
                Place travelsTo = new Place(data[4]);
                String arrivalTime = data[5];
                String arrivalDate = data[6];
                String departTime = data[7];
                String departDate = data[8];

                TransportType transport = null;
                switch (transportName) {
                    case "Helicopter":
                        transport = new Helicopter(arrivalTime, arrivalDate, travelsTo, departTime, price, travelsFrom, departDate);
                        break;
                    case "Limousine":
                        transport = new Limousine(arrivalTime, arrivalDate, travelsTo, departTime, price, travelsFrom, departDate);
                        break;
                    case "PrivateJet":
                        transport = new PrivateJet(arrivalTime, arrivalDate, travelsTo, departTime, price, travelsFrom, departDate);
                        break;
                    case "Yacht":
                        transport = new Yacht(arrivalTime, arrivalDate, travelsTo, departTime, price, travelsFrom, departDate);
                        break;
                }
                packageList.add(new Package(price, hoursOfTravelTime, travelsFrom, travelsTo, transport));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of the PackageList class.
     *
     * @return The instance of the PackageList class.
     */
    public static PackageList getInstance() {
        return uniqueInstance;
    }

    /**
     * Returns the list of packages.
     *
     * @return The list of packages.
     */
    public ArrayList<Package> getPackageList() {
        return packageList;
    }
}
