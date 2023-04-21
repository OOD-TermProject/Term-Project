package com.termproject.Strategy;

import com.termproject.Trip.Trip;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class RWStrategy {
  abstract void saveTrip(int tripID);
  public abstract Trip loadTrip(int tripID) throws FileNotFoundException;
  public abstract ArrayList<Trip> getAllTrips();

  public abstract int getMaxTripID();
}