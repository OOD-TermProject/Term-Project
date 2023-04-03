package com.termproject.Trip;

import com.termproject.People.TravelAgent;
import com.termproject.People.Traveler;

public class Trip {
    public int UniqueId;
    public String ThankYouNote;
    public TravelAgent Agent;
    public Traveler[] Travelers;
    public Itinerary Itinerary;
    public Reservation[] Reservations;
    public com.termproject.Payment.Bill Bill;

    public Trip() {

    }

    public boolean AddReservation(Reservation rsv) {
        // If reservation was successful, return true
        return true;
    }

    public void AddTraveler(Traveler traveler) {

    }

    public void AddThankYouNote(String note) {

    }
}
