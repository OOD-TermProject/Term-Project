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
    public void setState(State state) {
        this.state = state;
    }

    public Trip(int uniqueId) {
        this.setState(new AwaitTravelersState());
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public TravelAgent getAgent() {
        return agent;
    }

    public ArrayList<Traveler> getTravelers() {
        return travelers;
    }

    @Override
    public String toString() {
        String completedString = String.format("%s:\tAgent: %s. %s travelers: ", uniqueId, agent.getName(), travelers.size());
        for (int i = 0; i < (travelers.size() - 1); i++) {
            completedString = completedString + travelers.get(i) + ", ";
        }
        completedString = completedString + travelers.get(travelers.size() - 1);
        return completedString;
    }

    public boolean AddReservation(Reservation rsv) {
        // If reservation was successful, return true
        return true;
    }

}
