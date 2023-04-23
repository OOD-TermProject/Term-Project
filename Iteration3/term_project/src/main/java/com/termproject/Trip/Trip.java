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
    public Itinerary itinerary;
    public ArrayList<Reservation> reservations;
    public Bill bill;
    private State state;

    public Trip(int uniqueId) {
        this.setState(new AwaitTravelersState(this));
        this.uniqueId = uniqueId;
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
    public void addTraveler(Traveler traveler) {
        // Get the name of the passed-in traveler we'd like to add
        String thisName = traveler.getName();
        // Loop over each traveler in the list. If it's already there, return
        for (Traveler alreadyIncluded : travelers) {
            if (alreadyIncluded.getName().equalsIgnoreCase(thisName)) {
                return;
            }
        }
        // If we haven't returned by now, this traveler must not be in the list. Add them and return
        travelers.add(traveler);
    }
    public void removeTraveler(Traveler traveler) {
        // Loop over each traveler in the travelers list and look for a matching name
        for (Traveler alreadyIncluded : travelers) {
            if (alreadyIncluded.getName().equalsIgnoreCase(traveler.getName())) {
                // If we found a matching name, remove it from the list
                travelers.remove(alreadyIncluded);
                return;
            }
        }
    }
    public Itinerary getItinerary() {
        return this.itinerary;
    }
    public void setItinerary(Itinerary itineraryToAdd) {
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
    public void setReservations(ArrayList<Reservation> newReservations) {
        this.reservations = newReservations;
    }
    public void addReservation(Reservation reservationToAdd) {
        this.reservations.add(reservationToAdd);
    }
    public void removeReservation(Reservation reservationToRemove) {
        if (reservations.contains(reservationToRemove)) {
            reservations.remove(reservationToRemove);
        }
    }
    public void clearAllReservations() {
        this.reservations = null;
    }
    public Bill getBill(){
        return this.bill;
    }
    public void setBill(Bill newBill){
        this.bill = newBill;
    }
    public void removeBill() {
        this.bill = null;
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
        if (this.travelers.size() > 0) {
            // Show the number of travelers
            completedString += String.format("%s traveler", this.travelers.size());
            // Add an 's' to the end of 'traveler' to show it's plural
            if (this.travelers.size() > 1) {
                completedString += "s";
            }
            // Add colon before name list
            completedString += ": ";
            // Add the name of each traveler to the string
            for (int i = 0; i < (travelers.size()); i++) {
                // Append each traveler's name and a comma
                completedString = completedString + travelers.get(i) + ", ";
            }
            // Remove the comma after the last traveler's name and add a period
            completedString = completedString.substring(0, completedString.length() -1) + ". ";
        } else {
            completedString += "No travelers added. ";
        }
        completedString = completedString + "State: " + state;
        return completedString;
    }
}
