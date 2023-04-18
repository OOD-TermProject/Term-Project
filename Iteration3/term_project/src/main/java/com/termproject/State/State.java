package com.termproject.State;

public interface State {

	public void CreateTrip();
	
	public void AddTraveler(Object traveler);
	
	public boolean AddPackage(Object pkg);
	
	public void ApplyPayment(Object bill);
	
	public void AddThankYouNote(String note);
	
	public String GenerateItinerary();
	
	public void Save(Object trip);
	
	public Object Resume();
	
}
