package com.termproject.People;

public abstract class Person {
    public String name;
    public String mobilePhone;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
