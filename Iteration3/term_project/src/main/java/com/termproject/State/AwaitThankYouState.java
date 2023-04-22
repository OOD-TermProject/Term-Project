package com.termproject.State;

public class AwaitThankYouState implements State {
	
	private String thankYouNote;
	
	public void addThankYouNote (String note) {
		
		thankYouNote = note;
		
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
	public State advanceState() {
		
		return new ItineraryReadyState();
		
	}
	
	@Override
	public String toString() {
		return "Awaiting thank you note";
	}

}
