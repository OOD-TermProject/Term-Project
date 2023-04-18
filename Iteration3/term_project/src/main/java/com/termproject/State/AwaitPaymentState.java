package com.termproject.State;

public class AwaitPaymentState implements State {
	
	Object object;
	
	public AwaitPaymentState(Object object) {
		
		this.object = object;
		
	}
	
	@Override
	public void ApplyPayment(Object bill) {
		// TODO Auto-generated method stub
		
	}
	
	//************************************************

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
	public void AddThankYouNote(String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GenerateItinerary() {
		// TODO Auto-generated method stub
		return null;
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
