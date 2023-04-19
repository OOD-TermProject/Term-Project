package com.termproject.Strategy;

import com.termproject.Trip.Trip;

import java.util.ArrayList;

public class JSONStrategy extends RWStrategy {

    private static String filePath = "term_project/src/main/java/com/termproject/trips.json";

    /**
     * @param tripID
     */
    @Override
    void saveTrip(int tripID) {

    }

    /**
     * @param tripID
     * @return
     */
    @Override
    Trip loadTrip(int tripID) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Trip> getAllTrips() {
        return null;
    }

    public int getMaxTripID() {
        return 0;
    }
}