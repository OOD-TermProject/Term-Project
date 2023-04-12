package com.termproject.Decorator;

public class HeaderDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	String agentName;
	String agentPhone;
	String agentNote;
	
	public HeaderDecorator(Itinerary itinerary, String agentName, String agentPhone, String agentNote) {
		
		this.itinerary = itinerary;
		this.agentName = agentName;
		this.agentPhone = agentPhone;
		this.agentNote = agentNote;
		
	}

	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + "Itinerary by " + agentName + ", " + agentPhone + "\n" + agentNote + "\n";
		
	}
	
}
