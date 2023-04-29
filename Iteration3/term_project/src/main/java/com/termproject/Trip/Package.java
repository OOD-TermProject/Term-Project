package com.termproject.Trip;

import com.termproject.Transport.TransportType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @file Package.java
 *
 * Represents a travel package that includes a price, hours of travel time, origin and destination Places,
 * and the mode of transport.
 */

public class Package {
    /**
     * The price of the travel package.
     */
    private final float price;

    /**
     * The number of hours it takes to travel from the origin Place to the destination Place.
     */
    private final int hoursOfTravelTime;

    /**
     * The Place where the travel package originates from.
     */
    private final Place travelsFrom;

    /**
     * The Place where the travel package is going to.
     */
    private final Place travelsTo;

    /**
     * The mode of transport for the travel package.
     */
    private final TransportType transport;

    private static DateTimeFormatter timeFormatter;

    public LocalTime getDepartTime() {
        return LocalTime.parse(departTime, timeFormatter);
    }

    public LocalTime getArrivalTime() {
        return LocalTime.parse(arrivalTime, timeFormatter);
    }

    private final String departTime;
    private final String arrivalTime;

    /**
     * Creates a new travel package with the specified price, travel time, origin and destination Places,
     * and mode of transport.
     *
     * @param price The price of the travel package.
     * @param hoursOfTravelTime The number of hours it takes to travel from the origin Place to the destination Place.
     * @param travelsFrom The Place where the travel package originates from.
     * @param travelsTo The Place where the travel package is going to.
     * @param transport The mode of transport for the travel package.
     */
    public Package(float price, int hoursOfTravelTime, Place travelsFrom, Place travelsTo, TransportType transport, String departTime, String arrivalTime) {
        this.price = price;
        this.hoursOfTravelTime = hoursOfTravelTime;
        this.travelsFrom = travelsFrom;
        this.travelsTo = travelsTo;
        this.transport = transport;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    }

    /**
     * Returns the price of the travel package.
     *
     * @return The price of the travel package.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Returns the number of hours it takes to travel from the origin Place to the destination Place.
     *
     * @return The number of hours it takes to travel from the origin Place to the destination Place.
     */
    public int getHoursOfTravelTime() {
        return hoursOfTravelTime;
    }

    /**
     * Returns the Place where the travel package originates from.
     *
     * @return The Place where the travel package originates from.
     */
    public Place getTravelsFrom() {
        return travelsFrom;
    }

    /**
     * Returns the Place where the travel package is going to.
     *
     * @return The Place where the travel package is going to.
     */
    public Place getTravelsTo() {
        return travelsTo;
    }

    /**
     * Returns the mode of transport for the travel package.
     *
     * @return The mode of transport for the travel package.
     */
    public TransportType getTransport() {
        return transport;
    }

    @Override
    public String toString() {
        return String.format("%02d hour trip from %s to %s by %s", this.getHoursOfTravelTime(), this.getTravelsFrom(), this.getTravelsTo(), this.getTransport().toString().toLowerCase());
    }

    public String getItineraryFormat() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);

        return String.format("%s from %s to %s, departing at %s and arriving at %s",
                this.transport.toString(),
                this.travelsFrom,
                this.travelsTo,
                LocalTime.parse(this.departTime).format(timeFormatter),
                LocalTime.parse(this.arrivalTime).format(timeFormatter));
    }
}
