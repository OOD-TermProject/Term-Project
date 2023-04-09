package com.termproject;

import com.termproject.People.TravelAgent;
import com.termproject.Singleton.AgentList;
import com.termproject.Singleton.PackageList;
import com.termproject.Singleton.PersonList;
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
    private Trip activeTrip;
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
    private static String currentAgent = null;

    /**
     * Loads the system's config file (config.properties) to determine the file format
     * used for reading/writing Trip data to disk
     *
     * @return data format as a string ("json" or "xml"). If an error occurs, returns null
     */
    private static String loadConfigFile() {
        try {
            FileInputStream persistenceFormat = new FileInputStream(configPath);
            Properties props = new Properties();
            props.load(persistenceFormat);
            return props.getProperty("PERSISTENCE_FORMAT");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Allows the user to log into the system by checking their login again a list of travel agents
     *
     */
    private static void logIn(){
        System.out.println("Welcome to the Booking System!");
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter your username or type 'list' to see all users:");
            String username = scan.nextLine();
            if (username.equals("list")){
                for (TravelAgent agent : listOfAgents){
                    System.out.println(agent.getName() + ": " + agent.getUsername());
                }
            } else {
                for (TravelAgent agent : listOfAgents) {
                    if (agent.getUsername().equals(username)) {
                        currentAgent = agent.getName();
                        System.out.println("Welcome, " + currentAgent + "!");
                        return;
                    }
                }
                System.out.println("Username not found. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        String line = "==============================";
        String format = loadConfigFile();
        System.out.println("Loaded config: " + format);

        logIn();
        if (currentAgent == null) {
            doExit("Login failed. Aborting.");
        }
        System.out.println(line);
        int newOrExisting = mainMenu();
        if (newOrExisting == 1) {
            System.out.println("TODO: Call method to create a new trip");
        } else if (newOrExisting == 2) {
            System.out.println("TODO: Call method to load existing trip");
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
}
