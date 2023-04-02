package com.termproject.Strategy;

import com.termproject.Trip.Trip;

public class JSONStrategy extends RWStrategy {
    public void SaveData() {

    }

    public Trip ReadData() {
        return new Trip();
    }
}