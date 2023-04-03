package com.termproject;

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
    static ArrayList<String> listOfAgents = agents.getAgentList();
    private static final String configPath = "term_project/src/main/java/com/termproject/config.properties";
    private static final Scanner scan = new Scanner(System.in);
    private static String currentAgent;

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
        System.out.println("Welcome to the Booking System!\n");
        while (true) {
            System.out.println("Enter your name: ");
            String name = scan.nextLine();

            if (name.equalsIgnoreCase("list")) {
                System.out.println("Registered users:");
                for (String agent : listOfAgents) {
                    System.out.println("\t" + agent);
                }
                System.out.println("\n");
            } else if (listOfAgents.contains(name)) {
                currentAgent = name;
                System.out.println("Welcome, " + currentAgent + "!");
                break;
            } else {
                System.out.println("User not found. Please try again or type 'list' to see a list of registered users.");
            }
        }
    }

    public static void main(String[] args) {
        String format = loadConfigFile();
        System.out.println("Loaded config: " + format);

        logIn();
    }
}
