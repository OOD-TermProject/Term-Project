package com.termproject.People;

public class Traveler extends Customer {

    private final String customerType = "Traveler";

    /**
     * Traveler's favorite drink
     */
    private final String favoriteDrink;

    /**
     * Creates a new travel agent with the given name, cell number, and login
     *
     * @param name The name of the travel agent.
     * @param mobilePhone The cellphone number of the travel agent.
     * @param favoriteDrink This traveler's favorite drink
     */

    public Traveler(String name, String mobilePhone, String favoriteDrink) {
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.favoriteDrink = favoriteDrink;
    }

    /**
     * Returns the traveler's name
     *
     * @return The traveler's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the traveler's cellphone number
     *
     * @return The traveler's cellphone number
     */
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * Returns the travel agent's login for the reservation system
     *
     * @return The travel agent's login for the reservation system
     */
    public String getFavoriteDrink() {
        return this.favoriteDrink;
    }

}
