package com.termproject.Decorator;

import com.termproject.Trip.Trip;

public class ThankYouDecorator extends ItineraryDecorator {

	transient Itinerary itinerary;
	transient String thankYouNote;
	
	public ThankYouDecorator(Itinerary itinerary, Trip trip) {
		
		this.itinerary = itinerary;
		this.thankYouNote = trip.getThankYouNote();
		
	}
	
	@Override
	public String getItinerary() {
		
		return this.itinerary.getItinerary() + "\n" + this.thankYouNote + "\n";
		
	}

}
