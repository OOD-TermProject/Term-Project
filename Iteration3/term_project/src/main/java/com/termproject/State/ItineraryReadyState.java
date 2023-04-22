package com.termproject.State;

public class ItineraryReadyState implements State {
	
	private String itinerary;
	
	
	public String generateItinerary() {
		
		return itinerary;
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public State resume() {

		return this;
		
	}

	@Override
	public State advanceState() {		//Last state, should not advance any further;
		
		return this;					//New trip should be created after reaching this state
		
	}

	@Override
	public String toString() {
		return "Trip complete. Itinerary ready!";
	}
	
}
