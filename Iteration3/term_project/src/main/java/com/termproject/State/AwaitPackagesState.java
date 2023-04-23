package com.termproject.State;

import java.util.ArrayList;
import java.util.List;

import com.termproject.Trip.Package;
import com.termproject.Trip.Trip;

public class AwaitPackagesState implements State {
	
	private transient Trip thisTrip;
	private List<Package> pkgs;
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
	public void save() {
		// TODO Auto-generated method stub
	}
	@Override
	public State resume() {
		return this;
	}
	@Override
	public State advanceState() {
		return new AwaitPaymentState(thisTrip);
	}
	@Override
	public String toString() {
		return "Awaiting packages";
	}
	@Override
	public String getFutureVerb() {
		return this.futureVerb;
	}
	@Override
	public String getPastVerb() {
		return this.pastVerb;
	}

}
