package com.termproject.Transport;

import com.termproject.Trip.Place;

/**
 * @file TransportType.java
 * This file contains the abstract TransportType class which represents a type of transport for a travel package.
 */
public abstract class TransportType {
    /**
     * The arrival time of the transport.
     */
    protected String arrivalTime;

    /**
     * The arrival date of the transport.
     */
    protected String arrivalDate;

    /**
     * The destination of the transport.
     */
    protected Place destination;

    /**
     * The departure time of the transport.
     */
    protected String departTime;

    /**
     * The price of the transport.
     */
    protected float price;

    /**
     * The place the transport departs from.
     */
    protected Place departFrom;

    /**
     * The departure date of the transport.
     */
    protected String departDate;

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
    public TransportType(String arrivalTime, String arrivalDate, Place destination, String departTime, float price,
                         Place departFrom, String departDate) {
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.destination = destination;
        this.departTime = departTime;
        this.price = price;
        this.departFrom = departFrom;
        this.departDate = departDate;
    }

    /**
     * Getter for the arrival time of the transport.
     *
     * @return The arrival time of the transport.
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Getter for the arrival date of the transport.
     *
     * @return The arrival date of the transport.
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Getter for the destination of the transport.
     *
     * @return The destination of the transport.
     */
    public Place getDestination() {
        return destination;
    }

    /**
     * Getter for the departure time of the transport.
     *
     * @return The departure time of the transport.
     */
    public String getDepartTime() {
        return departTime;
    }

    /**
     * Getter for the price of the transport.
     *
     * @return The price of the transport.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Getter for the place the transport departs from.
     *
     * @return The place the transport departs from.
     */
    public Place getDepartFrom() {
        return departFrom;
    }

    /**
     * Getter for the departure date of the transport.
     *
     * @return The departure date of the transport.
     */
    public String getDepartDate() {
        return departDate;
    }
}