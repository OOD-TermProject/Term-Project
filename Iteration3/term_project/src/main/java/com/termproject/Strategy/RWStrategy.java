package com.termproject.Strategy;

import com.termproject.Trip.Trip;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class RWStrategy {

  public abstract void saveTrip(Trip tripToSave);

  public abstract Trip loadTrip(int tripID) throws FileNotFoundException;
  public abstract ArrayList<Trip> getAllTrips();

  public abstract int getMaxTripID();
}