package com.termproject.Decorator;

public class TripDetailDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	String[] transport;
	String[] departFrom;
	String[] destination;
	String[] departDate;
	String[] departTime;
	String[] arrivalDate;
	String[] arrivalTime;
	
	public TripDetailDecorator(Itinerary itinerary, String[] transport, String[] departFrom, String[] destination,
			String[] departDate, String[] departTime, String[] arrivalDate, String[] arrivalTime) {
		
		this.itinerary = itinerary;
		this.transport = transport;
		this.departFrom = departFrom;
		this.destination = destination;
		this.departDate = departDate;
		this.departTime = departTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		
	}
	
	public String PrintTripDetails(String[] transport, String[] departFrom, String[] destination,
			String[] departDate, String[] departTime, String[] arrivalDate, String[] arrivalTime) {
		
		String result = "Trip Details, beginning " + departDate[0] + " at " + departTime[0] + " and ending on " +
				arrivalDate[arrivalDate.length - 1] + " at " + arrivalTime[arrivalTime.length - 1] + "\n";
		
		int count = 1;	//For numbering the list from 1
		
		for (int i = 0; i < transport.length; i++) {
			result += count + ". " + transport[i] + " from " + departFrom[i]  + " to " + destination[i] +
					"\n" + departDate[i] + " at " + departTime[i] + ", arriving " + arrivalDate[i] + 
					" at " + arrivalTime[i] + "\n";
			
			count++;
		}
		
		return result;
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + PrintTripDetails(transport, departFrom, destination, departDate,
				departTime, arrivalDate, arrivalTime);
		
	}
	
}
