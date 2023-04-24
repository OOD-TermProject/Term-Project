package com.termproject.State;

import java.util.ArrayList;
import java.util.Scanner;

import com.termproject.Trip.Package;
import com.termproject.Trip.Trip;

public class AwaitPackagesState implements State {

	private transient Trip thisTrip;
	private ArrayList<Package> pkgs;
	public final String className = "AwaitPackagesState";
	private static final String futureVerb = "Add packages to trip";
	private static final String pastVerb = "Done adding packages";

	public AwaitPackagesState(Trip currentTrip) {
		pkgs = new ArrayList<>();
		thisTrip = currentTrip;
	}

	public void addPackage(Package pkg) {
		pkgs.add(pkg);
	}

	@Override
	public State advanceState() {
		// Do not let user advance the state if they haven't added any packages
		if (!(this.pkgs.size() > 1)) {
			System.out.println("You must add at least one package before moving on.");
			return this;
		}
		return new AwaitPaymentState(thisTrip);
	}

	/**
	 * @return
	 */
	@Override
	public String getStateInfo() {
		if ((pkgs != null) && (pkgs.size() > 0)) {
			String completedString = String.format("%s packages:\n", pkgs.size());
			for (Package pkg : pkgs) {
				completedString += "\t" + pkg + "\n";
			}
			return completedString;
		} else {
			return "No packages yet!";
		}
	}

	@Override
	public String toString() {
		return "Awaiting packages";
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
