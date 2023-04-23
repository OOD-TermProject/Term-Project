package com.termproject.State;

import com.termproject.People.Traveler;
import com.termproject.Trip.Trip;

import java.util.ArrayList;

public class AwaitTravelersState implements State {
	
	private transient Trip thisTrip;
	private ArrayList<Traveler> travelers;
	public final String className = "AwaitTravelersState";
	private static final String futureVerb = "Add travelers to trip";
	private static final String pastVerb = "Done adding travelers";
	
	public AwaitTravelersState(Trip currentTrip) {
		thisTrip = currentTrip;
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
		thisTrip.setTravelers(travelers);
		return new AwaitPackagesState(thisTrip);
	}

	@Override
	public String toString() {
		return "Awaiting travellers";
	}
	@Override
	public String getFutureVerb() {
		return futureVerb;
	}
	@Override
	public String getPastVerb() {
		return pastVerb;
	}
}
