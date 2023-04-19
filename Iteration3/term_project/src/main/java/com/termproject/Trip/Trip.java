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

    public Trip() {
        this.setState(new AwaitTravelersState());
    }

    public boolean AddReservation(Reservation rsv) {
        // If reservation was successful, return true
        return true;
    }

}
