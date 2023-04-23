package com.termproject.State;

import com.termproject.Trip.Trip;

public interface State {
	public String getFutureVerb();
	public String getPastVerb();

	public void save();
	
	public State resume();
	
	public State advanceState();
	
}
