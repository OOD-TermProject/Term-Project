package com.termproject.People;

/**
 * Represents a travel agent
 */
public class TravelAgent extends Person {
    /**
     * Name of the travel agent to be used when generating the itinerary
     */
//    private final String name;

    /**
     * Phone number for the travel agent
     */
//    private final String mobilePhone;
    /**
     * Username which the agent will use to log into the system
     */
    private final String username;

    /**
     * Creates a new travel agent with the given name, cell number, and login
     *
     * @param name        The name of the travel agent.
     * @param mobilePhone The cellphone number of the travel agent.
     * @param username    The login of the travel agent.
     */
    public TravelAgent(String name, String mobilePhone, String username) {
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.username = username;
    }

    /**
     * Returns the travel agent's name
     *
     * @return The travel agent's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the travel agent's cellphone number
     *
     * @return The travel agent's cellphone number
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Returns the travel agent's login for the reservation system
     *
     * @return The travel agent's login for the reservation system
     */
    public String getUsername() {
        return username;
    }
}
