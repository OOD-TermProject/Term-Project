package com.termproject;

import com.termproject.People.Customer;
import com.termproject.People.Traveler;
import com.termproject.Singleton.PersonList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Test;

public class PersonListTest {

	@Test
	public void testPersonListContainsData() {
		
		PersonList personList = PersonList.getInstance();					//Get instance of PersonList class
		ArrayList<Customer> customers = personList.getPersonList();			//Use instance to get list of people
		
		assertNotNull(customers);											//Assert that arraylist of people is not null and not empty
		assertFalse(customers.isEmpty());
		
	}
	
}
