package com.termproject.State;

import com.termproject.Trip.Trip;

import java.util.Scanner;

public interface State {
	public String getFutureVerb();
	public String getPastVerb();
	public void doAction(Scanner scanner);
	
	public State advanceState();

	public String getStateInfo();
	
}
