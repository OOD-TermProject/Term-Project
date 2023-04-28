package com.termproject.State;

import com.termproject.Decorator.*;
import com.termproject.Trip.Trip;

import java.util.Scanner;

public class ItineraryReadyState implements State {

	public void setThisTrip(Trip thisTrip) {
		this.thisTrip = thisTrip;
	}
	private transient Trip thisTrip;
	private Itinerary itinerary;
	public final String className = "ItineraryReadyState";
	private static final String futureVerb = "Generate itinerary";
	private static final String pastVerb = "Mark trip as completed and exit";

	public ItineraryReadyState(Trip currentTrip) {
		this.thisTrip = currentTrip;
	}

	public void generateItinerary() {
		itinerary = new InProgressItinerary();
		itinerary = new HeaderDecorator(itinerary, thisTrip);
		itinerary = new TravelerDecorator(itinerary, thisTrip);
		itinerary = new TripDetailDecorator(itinerary, thisTrip);
		itinerary = new BookingDecorator(itinerary, thisTrip);
		itinerary = new BillingDecorator(itinerary, thisTrip);
		itinerary = new ThankYouDecorator(itinerary, thisTrip);
	}

	@Override
	public State advanceState() {        //Last state, should not advance any further;
		return this;
	}

	/**
	 * @return
	 */
	@Override
	public String getStateInfo() {
		if (itinerary == null) {
			return "Itinerary ready to be generated!\n";
		} else {
			return itinerary.getItinerary();
		}
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
		generateItinerary();
	}
}
