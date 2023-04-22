package com.termproject.State;

import java.util.ArrayList;
import java.util.List;

public class AwaitPackagesState implements State {
	
	private List<Package> pkgs;
	
	public AwaitPackagesState() {
		
		pkgs = new ArrayList<>();
		
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
		
		return new AwaitPaymentState();
		
	}

	
	
}
