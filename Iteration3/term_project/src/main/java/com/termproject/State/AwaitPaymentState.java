package com.termproject.State;
import com.termproject.Payment.Bill;
import com.termproject.Trip.Trip;

import java.util.Scanner;

public class AwaitPaymentState implements State {

	public void setThisTrip(Trip thisTrip) {
		this.thisTrip = thisTrip;
	}
	private transient Trip thisTrip;
	private Bill bill;
	public final String className = "AwaitPaymentState";
	private static final String futureVerb = "Add payment info to trip";
	private static final String pastVerb = "Done with payment info";

	public AwaitPaymentState(Trip currentTrip) {
		thisTrip = currentTrip;
	}

	public void applyPayment(Bill bill) {
		//TODO
	}

	@Override
	public State advanceState() {
		// Do not let user advance the state if they haven't added payment info
		if (this.bill == null) {
			System.out.println("You must add at payment info before moving on.");
			return this;
		}
		thisTrip.setBill(bill);
		return new AwaitThankYouState(thisTrip);
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

	/**
	 * @param scanner
	 */
	@Override
	public void doAction(Scanner scanner) {

	}
}
