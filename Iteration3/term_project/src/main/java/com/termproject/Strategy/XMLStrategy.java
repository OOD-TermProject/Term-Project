package com.termproject.Strategy;

import com.termproject.Trip.Trip;

import java.util.ArrayList;

public class XMLStrategy extends RWStrategy {

    private static final String filePath = "term_project/src/main/java/com/termproject/trips.xml";
    private static ArrayList<Trip> tripList;

    /** Saves trip to disk
     * @param tripToSave
     */
    @Override
    public void saveTrip(Trip tripToSave) {

    }

    /** Loads a specific trip for the given ID
     * @param tripID
     * @return Trip object if the trip with matching ID was found. Else, null
     */
    @Override
    public Trip loadTrip(int tripID) {
        System.out.println("[XMLStrategy] Attempting to load trip #" + tripID);
        for (Trip trip : tripList) {
            if (trip.getUniqueId() == tripID) {
                return trip;
            }
        }
        return null;
    }

    /** Loads all trips from the XML file
     * @return ArrayList of all trips read in
     */
    @Override
    public ArrayList<Trip> getAllTrips() {
        return null;
    }

    /** Get the highest numbered trip that we know about
     * @return int Maximum trip ID
     */
    @Override
    public int getMaxTripID() {
        int x = 0;
        for (Trip trip : tripList) {
            int thisTripID = trip.getUniqueId();
            if (thisTripID > x) {
                x = thisTripID;
            }
        }
        return x;
    }
}