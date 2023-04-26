package com.termproject.Transport;

import com.termproject.Trip.Place;

public class Helicopter extends TransportType{
    private final String transportName = "Helicopter";

    /**
     * Constructor for the TransportType class.
     */
    public Helicopter() {
        super();
    }

    @Override
    public String toString() {
        return "Helicopter";
    }
}
