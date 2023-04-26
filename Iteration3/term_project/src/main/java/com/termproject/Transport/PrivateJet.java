package com.termproject.Transport;

import com.termproject.Trip.Place;

public class PrivateJet extends TransportType{

    private final String transportName = "PrivateJet";

    /**
     * Constructor for the TransportType class.
     */
    public PrivateJet() {

    }

    @Override
    public String toString() {
        return "Private Jet";
    }

}
