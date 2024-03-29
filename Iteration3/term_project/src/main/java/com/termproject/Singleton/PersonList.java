package com.termproject.Singleton;

import com.termproject.People.Customer;
import com.termproject.People.Traveler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PersonList {
    private static final String peopleFile = "term_project/src/main/java/com/termproject/Singleton/people.txt";
    private static final PersonList uniqueInstance = new PersonList();
    ArrayList<Customer> personList = new ArrayList<>();

    private PersonList() {
        loadPersonFile();
    }

    public static PersonList getInstance() {
        return uniqueInstance;
    }

    private void loadPersonFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(peopleFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String mobilePhone = data[1];
                String favoriteDrink = data[2];
                personList.add(new Traveler(name, mobilePhone, favoriteDrink));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getPersonList() {
        return personList;
    }
}
