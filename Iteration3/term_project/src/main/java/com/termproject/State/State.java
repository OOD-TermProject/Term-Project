package com.termproject.State;

import com.termproject.Trip.Trip;

import java.util.Scanner;

public interface State {
    String getFutureVerb();

    String getPastVerb();

    void doAction(Scanner scanner);

    State advanceState();

    String getStateInfo();

    void setThisTrip(Trip trip);
}
