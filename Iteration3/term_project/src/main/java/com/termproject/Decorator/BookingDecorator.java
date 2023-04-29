package com.termproject.Decorator;

import com.termproject.Trip.Trip;

public class BookingDecorator extends ItineraryDecorator {

	transient Itinerary itinerary;
	transient String agentName;
	transient String agentPhone;
	
	public BookingDecorator(Itinerary itinerary, Trip trip) {
		
		this.itinerary = itinerary;
		this.agentName = trip.getAgent().getName();
		this.agentPhone = trip.getAgent().getMobilePhone();
		
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + "\nBooking: \n\n\tEvery detail of your trip was booked with care by " +
				agentName + ". If you have any questions or problems, call " + agentName + " at " + agentPhone + 
				" any time, 24 hours a day.\n";
		
	}
	
}
