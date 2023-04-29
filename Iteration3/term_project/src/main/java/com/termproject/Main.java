package com.termproject;

import com.termproject.Factory.ReadFactory;
import com.termproject.Factory.WriteFactory;
import com.termproject.People.TravelAgent;
import com.termproject.Singleton.AgentList;
import com.termproject.Singleton.PersonList;
import com.termproject.Strategy.RWStrategy;
import com.termproject.Trip.Trip;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * @file Main.java
 * <p>
 * The main class for the reservation program
 */
public class Main {
    private static final AgentList agents = AgentList.getInstance();
    private static final String configPath = "term_project/src/main/java/com/termproject/config.properties";
    private static final Scanner scan = new Scanner(System.in);
    private static final ReadFactory readFactory = new ReadFactory();
    private static final WriteFactory writeFactory = new WriteFactory();
    private static final String line = "==============================";
    static ArrayList<TravelAgent> listOfAgents = agents.getAgentList();
    /**
     * The trip currently being modified
     *
     * @param activeTrip The trip being modified
     */
    private static Trip activeTrip;
    private static TravelAgent currentAgent = null;
    private static RWStrategy readStrategy;
    private static RWStrategy writeStrategy;
    private static String dataFormat;
    private static ArrayList<Trip> tripList;
    /**
     * Singleton for retrieving all current packages available in the system
     *
     * @param packages The singleton which provides a list of all packages
     */
    private final PersonList people = PersonList.getInstance();

    /**
     * Loads the system's config file (config.properties) to determine the file format
     * used for reading/writing Trip data to disk
     *
     * @return data format as a string ("json" or "xml"). If an error occurs, returns null
     */
    private static void loadConfigFile() {
        try {
            FileInputStream persistenceFormat = new FileInputStream(configPath);
            Properties props = new Properties();
            props.load(persistenceFormat);
            dataFormat = props.getProperty("PERSISTENCE_FORMAT");
            readStrategy = readFactory.createStrategy(dataFormat);
            writeStrategy = writeFactory.createStrategy(dataFormat);
            tripList = readStrategy.getAllTrips();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows the user to log into the system by checking their login again a list of travel agents
     */
    private static void logIn() {
        System.out.println("\n\n" + line + "\nWelcome to the Booking System!\n");
        while (true) {
            System.out.print("Please enter your username or type 'list' to see all users: ");
            String username = scan.nextLine();
            if (username.equals("list")) {
                for (TravelAgent agent : listOfAgents) {
                    System.out.println(agent.getName() + ": " + agent.getUsername());
                }
            } else {
                for (TravelAgent agent : listOfAgents) {
                    if (agent.getUsername().equals(username)) {
                        currentAgent = agent;
                        System.out.println("\n\nWelcome, " + currentAgent.getName() + "!");
                        return;
                    }
                }
                System.out.println("Username not found. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        // Do application setup tasks
        loadConfigFile();
        System.out.println("System configured to use " + dataFormat);

        // Send user to the login screen
        logIn();
        // If login failed for some reason, abort
        if (currentAgent == null) {
            doExit("Login failed. Aborting.");
        }
        System.out.println(line);

        // Send the user to the main menu
        int newOrExisting = mainMenu();

        // Continue based on the user's choice of "new trip" or "existing trip"
        if (newOrExisting == 1) {
            startNewTrip();
        } else if (newOrExisting == 2) {
            // Send the user to the "trip select" screen
            tripSelect();
        } else {
            doExit("Invalid value for newOrExisting. This should not happen. Aborting.");
        }

        // Ensure we have an active trip. If so, allow the user to modify it. If not, abort
        if (activeTrip != null) {
            modifyTrip();
        } else {
            doExit("Active trip improperly set. Aborting.");
        }
    }

    private static void doExit(String message) {
        System.out.println(message);
        scan.close();
        System.exit(-1);
    }

    private static int mainMenu() {
        while (true) {
            System.out.println("Please select an option below:\n");
            System.out.println("\t1) Start a new trip");
            System.out.println("\t2) Work on an existing trip");
            System.out.println("\n" + line);
            System.out.print("Enter an option: ");
            String selection = scan.nextLine();

            if (selection.equals("1")) {
                return 1;
            } else if (selection.equals("2")) {
                return 2;
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private static void tripSelect() {
        // Update the trip list, just in case something changed
        tripList = readStrategy.getAllTrips();

        while (true) {
            // Print list of available trips
            printTrips();

            // Ask user for trip ID or let them start a new trip
            System.out.print("\n" + line + "\nPlease enter the ID of the trip you'd like to load, or type 'new' to start a new trip: ");
            // See whether we got an integer
            if (scan.hasNextInt()) {
                int tripInput = scan.nextInt();
                // Call nextLine again to prevent the newline character from causing the next nextLine to be empty
                scan.nextLine();
                // Run through the trip list and see whether we got a match on the ID
                for (Trip trip : tripList) {
                    // If we found a match, set activeTrip and return
                    if (trip.getUniqueId() == tripInput) {
                        activeTrip = trip;
                        activeTrip.getState().setThisTrip(activeTrip);
                        return;
                    }
                }
                System.out.println("No trip with that ID. Please try again.\n");
            } else {
                String tripInput = scan.nextLine();
                if (tripInput.equalsIgnoreCase("new")) {
                    startNewTrip();
                    return;
                } else {
                    System.out.println("Invalid entry. Please try again.");
                }
            }
        }
    }

    private static void modifyTrip() {
        System.out.println(line);
        while (true) {
            System.out.println("\n\n" + line + "\nWorking on trip #" + activeTrip.getUniqueId());
            System.out.println("Current state: " + activeTrip.getState());
            System.out.println(line + "\n");
            System.out.println(activeTrip.getState().getStateInfo());
            System.out.println("Options:");
            System.out.println("\t1) " + activeTrip.getState().getFutureVerb());
            System.out.println("\t2) " + activeTrip.getState().getPastVerb());
            System.out.println("\t3) Save this trip");
            System.out.println("\t4) Quit");
            System.out.print(line + "\nEnter an option: ");

            String userInput = scan.nextLine();
            switch (userInput) {
                case "1":
                    // Call the doAction() method for each state. Pass a ref to our Scanner obj so it can read input
                    activeTrip.getState().doAction(scan);
                    break;
                case "2":
                    // Advance the trip object to the next state
                    activeTrip.advanceState();
                    break;
                case "3":
                    // Use writeStrategy to save trip to disk
                    writeStrategy.saveTrip(activeTrip);
                    break;
                case "4":
                    // Quit
                    doExit("Thank you for using the reservation system. Goodbye!");
                    break;
                default:
                    // Ask user to try again
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    private static void printTrips() {
        System.out.println(line);
        System.out.println("Available trips:\n");
        // Print out each trip
        for (Trip trip : tripList) {
            System.out.println("\t" + trip);
        }
    }

    private static void startNewTrip() {
        // Create a new Trip object with a unique ID number
        activeTrip = new Trip(readStrategy.getMaxTripID() + 1);
        // Set the new trip's agent to the one that's currently logged in
        activeTrip.setAgent(currentAgent);
    }
}