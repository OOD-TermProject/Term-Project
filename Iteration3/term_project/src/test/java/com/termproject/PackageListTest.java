package com.termproject;

import com.termproject.Transport.*;
import com.termproject.Trip.Package;
import com.termproject.Trip.Place;
import com.termproject.Singleton.PackageList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Test;

public class PackageListTest {
	
	@Test
	public void testPackageListContainsData() {
		
		PackageList packageList = PackageList.getInstance();		//Get instance of PackageList class
		ArrayList<Package> pkgs = packageList.getPackageList();		//Use instance to get list of packages
		
		assertNotNull(pkgs);										//Assert that arraylist of packages is not null and not empty
		assertFalse(pkgs.isEmpty());
		
	}

}
