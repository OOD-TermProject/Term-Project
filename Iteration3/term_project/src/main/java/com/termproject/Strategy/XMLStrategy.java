package com.termproject.Strategy;

import com.termproject.Trip.Trip;

import java.util.ArrayList;

public class XMLStrategy extends RWStrategy {

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
    ArrayList<Trip> loadAllTrips() {
        return null;
    }
}