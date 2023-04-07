package com.termproject.Transport;

import com.termproject.Trip.Place;

public class PrivateJet extends TransportType{
    /**
     * Constructor for the TransportType class.
     *
     * @param arrivalTime The arrival time of the transport.
     * @param arrivalDate The arrival date of the transport.
     * @param destination The destination of the transport.
     * @param departTime  The departure time of the transport.
     * @param price       The price of the transport.
     * @param departFrom  The place the transport departs from.
     * @param departDate  The departure date of the transport.
     */
    public PrivateJet(String arrivalTime, String arrivalDate, Place destination, String departTime, float price, Place departFrom, String departDate) {
        super(arrivalTime, arrivalDate, destination, departTime, price, departFrom, departDate);
    }
}
