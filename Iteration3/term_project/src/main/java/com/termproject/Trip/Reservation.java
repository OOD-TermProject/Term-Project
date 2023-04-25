package com.termproject.Trip;

import java.util.ArrayList;

public class Reservation {
    public String departingOn;
    public String arrivingOn;
    public ArrayList<Package> packages;

    public boolean addPackage(Package pkg) {
        // Return true if package addition was successful
        return true;
    }

    public void removePackage(Package pkg) {

    @Override
    public String toString() {
        String completedString = String.format("Reservation from %s to %s with %s package", this.departingOn, this.arrivingOn, this.packages.size());
        if (this.packages.size() > 1) {
            completedString += "s";
        }
        return completedString;
    }
}
