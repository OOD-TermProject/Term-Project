package com.termproject.Singleton;

public class PersonList {
    private static PersonList uniqueInstance = new PersonList();

    private PersonList() {
        // Load the person list in here
    }

    public static PersonList getInstance() {
        return uniqueInstance;
    }
}
