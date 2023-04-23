package com.termproject.State;
import com.termproject.Payment.Bill;
import com.termproject.Trip.Trip;

public class AwaitPaymentState implements State {

	private Trip thisTrip;
	private Bill bill;
	public static final String className = "AwaitPaymentState";
	private static final String futureVerb = "Add payment info to trip";
	private static final String pastVerb = "Done with payment info";

	public AwaitPaymentState(Trip currentTrip) {
		thisTrip = currentTrip;
	}
	public void applyPayment(Bill bill) {
		//TODO
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
		thisTrip.setBill(bill);
		return new AwaitThankYouState(thisTrip);
	}
	@Override
	public String toString() {
		return "Awaiting payment";
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
