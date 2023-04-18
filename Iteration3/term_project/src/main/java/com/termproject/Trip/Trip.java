package com.termproject.Trip;

import com.termproject.People.TravelAgent;
import com.termproject.People.Traveler;
import com.termproject.State.AwaitTravelersState;
import com.termproject.State.State;

public class Trip {
    public int UniqueId;
    public String ThankYouNote;
    public TravelAgent Agent;
    public Traveler[] Travelers;
    public Itinerary Itinerary;
    public Reservation[] Reservations;
    public com.termproject.Payment.Bill Bill;
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
