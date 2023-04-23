package com.termproject.State;
import com.termproject.Trip.Trip;

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
	public void save() {
		// TODO Auto-generated method stub
	}

	@Override
	public State resume() {
		return this;
	}

	@Override
	public State advanceState() {
		thisTrip.setThankYouNote(thankYouNote);
		return new ItineraryReadyState(thisTrip);
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
}
