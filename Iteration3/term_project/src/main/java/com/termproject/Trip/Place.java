package com.termproject.Trip;

/**
 * @file Place.java
 *
 * Represents a place with a name.
 */
public class Place {
    private final String name;

    /**
     * Constructs a new Place object with the given name.
     *
     * @param name the name of the place
     */
    public Place(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this place.
     *
     * @return the name of this place
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}