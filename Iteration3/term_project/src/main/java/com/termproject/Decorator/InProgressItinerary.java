package com.termproject.Decorator;

public class InProgressItinerary extends Itinerary {

    transient String itinerary = "";

    public String getItinerary() {
        return itinerary;
    }
}
