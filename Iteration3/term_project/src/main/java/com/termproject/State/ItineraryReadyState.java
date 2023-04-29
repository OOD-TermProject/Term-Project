package com.termproject.State;

import com.termproject.Decorator.*;
import com.termproject.Trip.Trip;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ItineraryReadyState implements State {

	public void setThisTrip(Trip thisTrip) {
		this.thisTrip = thisTrip;
	}
	private transient Trip thisTrip;
	transient private Itinerary itinerary;
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

		String fileName = String.format("%s-%s.txt", thisTrip.getAgent().getName(), thisTrip.getUniqueId());

		try {
			FileWriter writer = new FileWriter(fileName);
			writer.write(itinerary.getItinerary());
			writer.close();
		} catch (IOException e) {
			System.out.println("File was unable to be saved to disk!");
			e.printStackTrace();
		}

		thisTrip.setItinerary(itinerary.getItinerary());
	}

	@Override
	public State advanceState() {
		thisTrip.complete();
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
