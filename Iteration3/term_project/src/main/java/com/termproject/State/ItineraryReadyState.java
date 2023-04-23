package com.termproject.State;

import com.termproject.Trip.Trip;

import java.util.Scanner;

public class ItineraryReadyState implements State {

	private transient Trip thisTrip;
	private String itinerary;
	public final String className = "ItineraryReadyState";
	private static final String futureVerb = "Generate itinerary";
	private static final String pastVerb = "Mark trip as completed and exit";

	public ItineraryReadyState(Trip currentTrip) {
		this.thisTrip = currentTrip;
	}

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
	public State advanceState() {        //Last state, should not advance any further;
		return this;                    //New trip should be created after reaching this state
	}

	/**
	 * @return
	 */
	@Override
	public String getStateInfo() {
		return null;
	}

	@Override
	public String toString() {
		return "Trip complete. Itinerary ready!";
	}

	@Override
	public String getFutureVerb() {
		return futureVerb;
	}

	@Override
	public String getPastVerb() {
		return pastVerb;
	}

	/**
	 * @param scanner
	 */
	@Override
	public void doAction(Scanner scanner) {

	}
}
