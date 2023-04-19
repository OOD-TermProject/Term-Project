package com.termproject.Strategy;

import com.termproject.Trip.Trip;

import java.util.ArrayList;

public abstract class RWStrategy {
  abstract void saveTrip(int tripID);
  abstract Trip loadTrip(int tripID);
  public abstract ArrayList<Trip> getAllTrips();

  public abstract int getMaxTripID();
}