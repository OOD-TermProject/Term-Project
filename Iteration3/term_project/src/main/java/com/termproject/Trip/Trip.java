package com.termproject.Trip;

import com.termproject.Payment.Bill;
import com.termproject.People.TravelAgent;
import com.termproject.People.Traveler;
import com.termproject.State.AwaitTravelersState;
import com.termproject.State.State;

import java.util.ArrayList;

public class Trip {
    public int uniqueId;
    public String thankYouNote;
    public TravelAgent agent;
    public ArrayList<Traveler> travelers;
    public String itinerary;
    public ArrayList<Reservation> reservations;
    public Bill bill;
    private State state;
    private boolean complete;

    public Trip(int uniqueId) {
        this.setState(new AwaitTravelersState(this));
        this.uniqueId = uniqueId;
        this.complete = false;
    }

    public boolean isComplete() {
        return complete;
    }

    public void complete() {
        this.complete = true;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getThankYouNote() {
        return this.thankYouNote;
    }

    public void setThankYouNote(String noteToAdd) {
        this.thankYouNote = noteToAdd;
    }

    public TravelAgent getAgent() {
        return agent;
    }

    public void setAgent(TravelAgent agent) {
        this.agent = agent;
    }

    public ArrayList<Traveler> getTravelers() {
        return travelers;
    }

    public void setTravelers(ArrayList<Traveler> travelers) {
        this.travelers = travelers;
    }

    public String getItinerary() {
        return this.itinerary;
    }

    public void setItinerary(String itineraryToAdd) {
        if (this.itinerary != null) {
            removeItinerary();
        }
        this.itinerary = itineraryToAdd;
    }

    public void removeItinerary() {
        this.itinerary = null;
    }

    public ArrayList<Reservation> getReservations() {
        return this.reservations;
    }

    public void addReservation(Reservation reservationToAdd) {
        if (this.reservations == null) {
            this.reservations = new ArrayList<>();
        }
        this.reservations.add(reservationToAdd);
    }

    public Bill getBill() {
        return this.bill;
    }

    public void setBill(Bill newBill) {
        this.bill = newBill;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void advanceState() {
        this.state = state.advanceState();
    }

    @Override
    public String toString() {
        // Begin string with unique ID # and agent's name
        String completedString = String.format("%s:\tAgent: %s. ", uniqueId, agent.getName());
        // If the trip has travellers already, display them.
        if ((this.travelers != null) && (this.travelers.size() > 0)) {
            // Show the number of travelers
            completedString += String.format("%s traveler", this.travelers.size());
            // Add an 's' to the end of 'traveler' to show it's plural
            if (this.travelers.size() > 1) {
                completedString += "s";
            }
            // Add colon before name list
            completedString += ":";
            // Add the name of each traveler to the string
            for (int i = 0; i < (travelers.size()); i++) {
                // Append each traveler's name and a comma
                completedString = completedString + " " + travelers.get(i) + ",";
            }
            // Remove the comma after the last traveler's name and add a period
            completedString = completedString.substring(0, completedString.length() - 1) + ". ";
        } else {
            completedString += "No travelers added. ";
        }
        completedString = completedString + "State: " + state;
        return completedString;
    }
}
