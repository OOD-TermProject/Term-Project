package com.termproject.Decorator;

import com.termproject.Trip.Trip;

public class BookingDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	String agentName;
	String agentPhone;
	
	public BookingDecorator(Itinerary itinerary, Trip trip) {
		
		this.itinerary = itinerary;
		this.agentName = trip.getAgent().getName();
		this.agentPhone = trip.getAgent().getMobilePhone();
		
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + "Booking: \nEvery detail of your trip was booked with care by " +
				agentName + ". If you have any questions or problems, call " + agentName + " at " + agentPhone + 
				" any time, 24 hours a day.\n";
		
	}
	
}
