package com.termproject.Decorator;

public class BookingDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	String agentName;
	String agentPhone;
	
	public BookingDecorator(Itinerary itinerary, String agentName, String agentPhone) {
		
		this.itinerary = itinerary;
		this.agentName = agentName;
		this.agentPhone = agentPhone;
		
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + "Booking: \nEvery detail of your trip was booked with care by " +
				agentName + ". If you have any questions or problems, call " + agentName + " at " + agentPhone + 
				" any time, 24 hours a day.\n";
		
	}
	
}
