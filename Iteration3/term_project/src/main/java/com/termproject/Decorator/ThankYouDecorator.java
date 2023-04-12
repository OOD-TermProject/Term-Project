package com.termproject.Decorator;

public class ThankYouDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	String thankYouNote;
	
	public ThankYouDecorator(Itinerary itinerary, String thankYouNote) {
		
		this.itinerary = itinerary;
		this.thankYouNote = thankYouNote;
		
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + thankYouNote + "\n";
		
	}

}
