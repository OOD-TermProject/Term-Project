package com.termproject.State;

public class ItineraryReadyState implements State {
	
	Object object;
	
	public ItineraryReadyState(Object object) {
		
		this.object = object;
		
	}
	
	@Override
	public String GenerateItinerary() {
		
		return "Itinerary";
		
	}
	
	//***********************************

	@Override
	public void CreateTrip() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddTraveler(Object traveler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean AddPackage(Object pkg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ApplyPayment(Object bill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddThankYouNote(String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Save(Object trip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object Resume() {
		// TODO Auto-generated method stub
		return null;
	}

}
