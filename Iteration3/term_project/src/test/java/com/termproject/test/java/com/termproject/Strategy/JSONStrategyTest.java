package com.termproject.Strategy;

import com.termproject.State.AwaitPackagesState;
import com.termproject.State.AwaitPaymentState;
import com.termproject.State.AwaitTravelersState;
import com.termproject.Trip.Trip;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JSONStrategyTest {

    @Test
    void testSaveReturnTripState() {

        Trip testTrip = new Trip(123);                      //Create test trip to be saved and loaded

        testTrip.setState(new AwaitPackagesState(testTrip));       //Set state of trip to AwaitTravelersState before saving

        JSONStrategy jsonStrategy = new JSONStrategy();             //Use the json strategy to save the trip to disk
        jsonStrategy.saveTrip(testTrip);

        ArrayList<Trip> loadedTrips = jsonStrategy.getAllTrips();   //Use the json strategy to load the trip from disk
        Trip loadedTrip = loadedTrips.get(0);

        assertEquals(testTrip.getUniqueId(), loadedTrip.getUniqueId());                     //Assert that saved and loaded trip are the same Trip object

        assertEquals(testTrip.getState().getClass(), loadedTrip.getState().getClass());     //Assert the Trip object has loaded into the same state it was saved in

    }

}