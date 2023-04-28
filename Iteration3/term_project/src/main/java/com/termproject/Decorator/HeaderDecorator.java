package com.termproject.Decorator;

import com.termproject.Trip.Trip;

public class HeaderDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	String agentName;
	String agentPhone;
	
	public HeaderDecorator(Itinerary itinerary, Trip trip) {
		
		this.itinerary = itinerary;
		this.agentName = trip.getAgent().getName();
		this.agentPhone = trip.getAgent().getMobilePhone();
		
	}

	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + "Itinerary by " + this.agentName + ", " + this.agentPhone + "\n";
		
	}
	
}
