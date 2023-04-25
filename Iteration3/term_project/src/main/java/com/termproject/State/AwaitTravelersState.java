package com.termproject.State;

import com.termproject.People.Traveler;
import com.termproject.Trip.Trip;

import java.util.ArrayList;
import java.util.Scanner;

public class AwaitTravelersState implements State {

	public void setThisTrip(Trip thisTrip) {
		this.thisTrip = thisTrip;
	}

	private transient Trip thisTrip;
	private ArrayList<Traveler> travelers;
	public final String className = "AwaitTravelersState";
	private static final String futureVerb = "Add travelers to trip";
	private static final String pastVerb = "Done adding travelers";

	public AwaitTravelersState(Trip currentTrip) {
		thisTrip = currentTrip;
		travelers = new ArrayList<>();
	}

	public void doAction(Scanner scanner) {
		if (travelers == null) {
			travelers = new ArrayList<>();
		}
		addTraveler(scanner);
	}

	public void addTraveler(Scanner scanner) {
		System.out.println("Enter the traveler's full name: ");
		String nameInput = scanner.nextLine();
		System.out.println("Enter the traveler's phone number: ");
		String phoneInput = scanner.nextLine();
		System.out.println("Enter the traveler's favorite drink: ");
		String drinkInput = scanner.nextLine();
		Traveler newTraveler = new Traveler(nameInput, phoneInput, drinkInput);
		travelers.add(newTraveler);
	}

	@Override
	public State advanceState() {
		// Do not let user advance the state if they haven't added any travelers
		if (!(this.travelers.size() > 1)) {
			System.out.println("You must add at least one traveler before moving on.");
			return this;
		}
		thisTrip.setTravelers(travelers);
		return new AwaitPackagesState(thisTrip);
	}

	@Override
	public String toString() {
		return "Awaiting travelers";
	}

	@Override
	public String getFutureVerb() {
		return futureVerb;
	}

	@Override
	public String getPastVerb() {
		return pastVerb;
	}

	public String getStateInfo(){
		if ((travelers != null) && (travelers.size() > 0)) {
			String completedString = String.format("%s travelers:\n", travelers.size());
			for (Traveler traveler : travelers) {
				completedString += "\t" + traveler.getName() + "\n";
			}
			return completedString;
		} else {
			return "No travelers yet!";
		}
	}
}
