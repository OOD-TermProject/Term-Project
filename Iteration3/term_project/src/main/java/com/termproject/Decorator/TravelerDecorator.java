package com.termproject.Decorator;

import com.termproject.People.Traveler;
import com.termproject.Trip.Trip;

import java.util.ArrayList;

public class TravelerDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	ArrayList<Traveler> travelerList;
	
	public TravelerDecorator(Itinerary itinerary, Trip trip) {
		
		this.itinerary = itinerary;
		this.travelerList = trip.getTravelers();
		
	}
	
	public String printTravelers() {
		
		String result = "This trip is booked for " + this.travelerList.size() + " traveler";
		if (this.travelerList.size() > 1) {
			result += "s";
		}
		result += ":\n";
		int count = 1;	//For numbering the list from 1
		
		for (int i = 0; i < travelerList.size(); i++) {
			result += "\t" + count + ". " + travelerList.get(i) + "\n";
			count++;
		}
		
		return result;
		
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + printTravelers() + "\n";
		
	}
	
}
