package com.termproject.State;

import com.termproject.Trip.Trip;

import java.util.Scanner;

public class AwaitThankYouState implements State {

    private static final String pastVerb = "Done with Thank You note";
    private static String futureVerb = "Add 'Thank You' note to trip";
    private static Scanner scan = null;
    public final String className = "AwaitThankYouState";
    private transient Trip thisTrip;
    private String thankYouNote = "";
    public AwaitThankYouState(Trip currentTrip) {
        this.thisTrip = currentTrip;
    }

    public void setThisTrip(Trip thisTrip) {
        this.thisTrip = thisTrip;
    }

    public void addThankYouNote() {
        System.out.print("Enter 'Thank You' note to attach to itinerary: ");
        thankYouNote = scan.nextLine();
        futureVerb = "Change 'Thank You' note";
    }

    @Override
    public State advanceState() {
        // Do not let user advance the state if they haven't added the Thank You note
        if ((this.thankYouNote == null) || this.thankYouNote.length() < 1) {
            System.out.println("You must add a Thank You note before moving on.");
            return this;
        }
        thisTrip.setThankYouNote(thankYouNote);
        return new ItineraryReadyState(thisTrip);
    }

    /**
     * @return
     */
    @Override
    public String getStateInfo() {
        if (thankYouNote == null) {
            thankYouNote = "";
        }
        if (thankYouNote.isEmpty()) {
            return "'Thank You' note currently empty. Enter a 'Thank You' note to proceed\n\n";
        } else {
            return "Current 'Thank You' note:\n\t\"" + thankYouNote + "\"\n\n";
        }
    }

    @Override
    public String toString() {
        return "Awaiting 'Thank You' note";
    }

    @Override
    public String getFutureVerb() {
        return futureVerb;
    }

    @Override
    public String getPastVerb() {
        return pastVerb;
    }

    /**
     * @param scanner
     */
    @Override
    public void doAction(Scanner scanner) {
        scan = scanner;
        if (thankYouNote == null) {
            thankYouNote = "";
        }
        addThankYouNote();
    }
}
