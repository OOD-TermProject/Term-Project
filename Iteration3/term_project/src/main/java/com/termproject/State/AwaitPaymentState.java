package com.termproject.State;

public class AwaitPaymentState implements State {
	
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
		
		return new AwaitThankYouState();
		
	}
	
	
	
}
