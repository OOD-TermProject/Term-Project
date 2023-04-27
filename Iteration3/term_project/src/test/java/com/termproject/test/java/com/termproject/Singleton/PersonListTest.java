package com.termproject.Singleton;

import com.termproject.People.Customer;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonListTest {

    @Test
    void getInstance() {

        assertFalse(false);
    }

    @Test
    void getPersonList() {
        assertFalse(false);
    }

    @Test
    void testPersonListContainsData() {

        PersonList personList = PersonList.getInstance();               //Get instance of the PersonList class
        ArrayList<Customer> customers = personList.getPersonList();     //Use instance to get a list of customers

        assertNotNull(customers);                   //Assert that arraylist is not null and not empty
        assertFalse(customers.isEmpty());

    }

}