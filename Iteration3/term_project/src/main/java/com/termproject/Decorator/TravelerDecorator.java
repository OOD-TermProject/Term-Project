package com.termproject.Decorator;

public class TravelerDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	String[] travelerList;
	
	public TravelerDecorator(Itinerary itinerary, String[] travelerList) {
		
		this.itinerary = itinerary;
		this.travelerList = travelerList;
		
	}
	
	public String PrintTravelers(String[] travelers) {
		
		String result = Integer.toString(travelers.length) + " travelers: \n";
		int count = 1;	//For numbering the list from 1
		
		for (int i = 0; i < travelers.length; i++) {
			result += count + ". " + travelers[i] + "\n";
			count++;
		}
		
		return result;
		
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + PrintTravelers(travelerList);
		
	}
	
}
