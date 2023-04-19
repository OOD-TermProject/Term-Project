package com.termproject;

import com.termproject.Factory.ReadFactory;
import com.termproject.Factory.WriteFactory;
import com.termproject.People.TravelAgent;
import com.termproject.Singleton.AgentList;
import com.termproject.Singleton.PackageList;
import com.termproject.Singleton.PersonList;
import com.termproject.Strategy.RWStrategy;
import com.termproject.Trip.Package;
import com.termproject.Trip.Trip;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * @file Main.java
 *
 * The main class for the reservation program
 *
 */
public class Main {
    /**
     * The trip currently being modified
     *
     * @param activeTrip The trip being modified
     */
    private static Trip activeTrip;
    /**
     * Singleton for retrieving all current packages available in the system
     *
     * @param packages The singleton which provides a list of all packages
     */
    private static final PackageList packages = PackageList.getInstance();
    static ArrayList<Package> listOfPackages = packages.getPackageList();
    private final PersonList people = PersonList.getInstance();
    private static final AgentList agents = AgentList.getInstance();
    static ArrayList<TravelAgent> listOfAgents = agents.getAgentList();
    private static final String configPath = "term_project/src/main/java/com/termproject/config.properties";
    private static final Scanner scan = new Scanner(System.in);
    private static TravelAgent currentAgent = null;
    private static final ReadFactory readFactory = new ReadFactory();
    private static final WriteFactory writeFactory = new WriteFactory();
    private static RWStrategy readStrategy;
    private static RWStrategy writeStrategy;
    private static String dataFormat;
    private static ArrayList<Trip> tripList;
    private static final String line = "==============================";
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
     *
     */
    private static void logIn(){
        System.out.println("Welcome to the Booking System!");
        while (true) {
            System.out.println("Please enter your username or type 'list' to see all users:");
            String username = scan.nextLine();
            if (username.equals("list")){
                for (TravelAgent agent : listOfAgents){
                    System.out.println(agent.getName() + ": " + agent.getUsername());
                }
            } else {
                for (TravelAgent agent : listOfAgents) {
                    if (agent.getUsername().equals(username)) {
                        currentAgent = agent;
                        System.out.println("Welcome, " + currentAgent.getName() + "!");
                        return;
                    }
                }
                System.out.println("Username not found. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        loadConfigFile();
        System.out.println("System configured to use " + dataFormat);

        logIn();
        if (currentAgent == null) {
            doExit("Login failed. Aborting.");
        }
        System.out.println(line);
        int newOrExisting = mainMenu();
        if (newOrExisting == 1) {
            // Create a new Trip object with a unique ID number
            activeTrip = new Trip(readStrategy.getMaxTripID() + 1);
        } else if (newOrExisting == 2) {
            activeTrip = getTripFromList();
        } else {
            doExit("Invalid value for newOrExisting. Aborting.");
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
            System.out.println("\t1. Start a new trip");
            System.out.println("\t2. Work on an existing trip");
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
    private static Trip getTripFromList() {
        while (true) {
            // Ask user for trip ID or if they'd like to see a list of trips
            System.out.println("Please enter the ID of the trip you'd like to load, or type 'list' to see all trips:");
            // See whether we got an integer
            if (scan.hasNextInt()) {
                int tripInput = scan.nextInt();
                // Run through the trip list and see whether we got a match on the ID
                for (Trip trip : tripList) {
                    // If we found a match, return the corresponding Trip object
                    if (trip.getUniqueId() == tripInput) {
                        return trip;
                    }
                }
            } else {
                String tripInput = scan.nextLine();
                if (tripInput.equalsIgnoreCase("list")) {
                    // Update the trip list, just in case something changed
                    tripList = readStrategy.getAllTrips();
                    System.out.println(line);
                    System.out.println("Available trips:");
                    // Print out each trip
                    for (Trip trip : tripList) {
                        System.out.println(trip);
                    }
                } else {
                    System.out.println("Invalid entry. Please try again.");
                }
            }
        }
    }

}