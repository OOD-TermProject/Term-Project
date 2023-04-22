package com.termproject.State;

import com.termproject.People.Traveler;
import com.termproject.Trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class AwaitTravelersState implements State {
	
	private Trip savedTrip;
	
	private List<Traveler> travelers;
	
	public AwaitTravelersState() {
		
		travelers = new ArrayList<>();
		
	}
	
	public void addTraveler(Traveler traveler) {
		
		travelers.add(traveler);
		
	}

	@Override
	public void save() {		//Write to disk?
		
		//TODO
		
	}

	@Override
	public State resume() {
		
		return this;
		
	}

	@Override
	public State advanceState() {

		return new AwaitPackagesState();
		
	}

}
