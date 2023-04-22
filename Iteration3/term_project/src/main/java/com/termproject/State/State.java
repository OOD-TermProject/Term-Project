package com.termproject.State;

public interface State {

	public void save();
	
	public State resume();
	
	public State advanceState();
	
}
