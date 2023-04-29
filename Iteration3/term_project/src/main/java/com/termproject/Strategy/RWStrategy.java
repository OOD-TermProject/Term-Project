package com.termproject.Strategy;

import com.termproject.Trip.Trip;

import java.util.ArrayList;

public abstract class RWStrategy {

    public abstract void saveTrip(Trip tripToSave);

    public abstract ArrayList<Trip> getAllTrips();

    public abstract int getMaxTripID();
}