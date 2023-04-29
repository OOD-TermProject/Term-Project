package com.termproject.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;

public class Reservation {
    private String arrivingOn;
    private String departingOn;
    private ArrayList<Package> packages;

    public Reservation(String departingOn, String arrivingOn) {
        this.departingOn = departingOn;
        this.arrivingOn = arrivingOn;
        this.packages = new ArrayList<>();
    }

    public String getArrivingOn() {
        return arrivingOn;
    }
    public void setArrivingOn(String arrivingOn) {
        this.arrivingOn = arrivingOn;
    }

    public String getDepartingOn() {
        return departingOn;
    }
    public void setDepartingOn(String departingOn) {
        this.departingOn = departingOn;
    }
    public ArrayList<Package> getPackages() {
        return packages;
    }
    public void addPackage(Package pkg) {
        if (this.packages == null) {
            this.packages = new ArrayList<>();
        }

        // Get times from package
        LocalTime departTime = pkg.getDepartTime();
        LocalTime arrivalTime = pkg.getArrivalTime();

        // Convert this reservation's dates to LocalDate objects
        LocalDate departDate = LocalDate.parse(departingOn);
        LocalDate arrivalDate = LocalDate.parse(arrivingOn);

        // Combine both into LocalDateTime objects
        LocalDateTime tempDepart = LocalDateTime.of(departDate, departTime);
        LocalDateTime tempArrive = LocalDateTime.of(arrivalDate, arrivalTime);

        // Convert them to Strings and set the fields for this reservation object
        arrivingOn = tempArrive.toString();
        departingOn = tempDepart.toString();

        this.packages.add(pkg);
    }
    public void removePackage(int index) {
        this.packages.remove(index);
    }

    @Override
    public String toString() {
        String completedString = String.format("Reservation from %s to %s with %s package", this.departingOn, this.arrivingOn, this.packages.size());
        if (this.packages.size() > 1) {
            completedString += "s";
        }
        return completedString;
    }
}
