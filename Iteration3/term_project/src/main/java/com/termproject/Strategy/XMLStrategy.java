package com.termproject.Strategy;

import com.termproject.Trip.Trip;

public class XMLStrategy extends RWStrategy {
    public void SaveData() {

    }

    public Trip ReadData() {
        return new Trip();
    }
}