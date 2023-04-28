package com.termproject.Decorator;

import com.termproject.Trip.Package;
import com.termproject.Trip.Reservation;
import com.termproject.Trip.Trip;

public class BillingDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	Trip thisTrip;
	
	public BillingDecorator(Itinerary itinerary, Trip trip) {
		this.itinerary = itinerary;
		this.thisTrip = trip;
	}
	
	@Override
	public String getItinerary() {
		String completedString = itinerary.getItinerary();
		completedString += "Billing Details\n";
		for (Reservation rsv : thisTrip.getReservations()) {
			for (Package pkg : rsv.getPackages()) {
				completedString += pkg.getItineraryFormat() + "\n$" + String.format("%.2f", pkg.getPrice()) + "\n\n";
			}
		}
		return completedString;
	}
	
}
