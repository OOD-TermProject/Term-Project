package com.termproject.State;
import com.termproject.Trip.Trip;

import java.util.Scanner;

public class AwaitThankYouState implements State {

	private transient Trip thisTrip;
	private String thankYouNote;
	public final String className = "AwaitThankYouState";
	private static final String futureVerb = "Add Thank You note to trip";
	private static final String pastVerb = "Done with Thank You note";

	public AwaitThankYouState(Trip currentTrip) {
		this.thisTrip = currentTrip;
	}

	public void addThankYouNote (String note) {
		thankYouNote = note;
	}

	@Override
	public State advanceState() {
		// Do not let user advance the state if they haven't added the Thank You note
		if ((this.thankYouNote == null) || this.thankYouNote.length() < 1) {
			System.out.println("You must add a Thank You note before moving on.");
			return this;
		}
		thisTrip.setThankYouNote(thankYouNote);
		return new ItineraryReadyState(thisTrip);
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
		return "Awaiting thank you note";
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
