package com.termproject.Strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import com.termproject.State.AwaitPaymentState;
import com.termproject.State.State;
import com.termproject.Transport.PrivateJet;
import com.termproject.Transport.TransportType;
import com.termproject.Trip.Trip;

public class JSONStrategy extends RWStrategy {
    // Path to the JSON file of trips
    private static final String filePath = "term_project/src/main/java/com/termproject/trips.json";
    private static ArrayList<Trip> tripList;

    /**Saves trip to disk
     * @param tripToSave
     */
    @Override
    public void saveTrip(Trip tripToSave) {
        System.out.println("Attempting to save trip!");
        Gson objectParser = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        // Loop over each trip in the tripList
        for (Trip trip : tripList) {
            // If we find a trip with the same unique ID, delete it
            if (trip.getUniqueId() == tripToSave.getUniqueId()) {
                tripList.remove(tripList.indexOf(trip));
                break;
            }
        }

        // Next add the passed-in Trip object to the ArrayList
        tripList.add(tripToSave);

        // Convert the ArrayList to a string
        String rawJSON = objectParser.toJson(tripList);

        System.out.println("Created JSON");
        System.out.println(rawJSON);

    }

    /**
     * @param tripID
     * @return
     */
    @Override
    public Trip loadTrip(int tripID) {
        System.out.println("[JSONStrategy] Attempting to load trip #" + tripID);
        for (Trip trip : tripList) {
            if (trip.getUniqueId() == tripID) {
                return trip;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Trip> getAllTrips() {
        // New ArrayList to hold all of the loaded trips
        ArrayList<Trip> tripList = new ArrayList<>();

        // Since we have to define custom deserializers, create a GsonBuilder and register them
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(TransportType.class, new TransportTypeDeserializer());
        builder.registerTypeAdapter(State.class, new StateDeserializer());
        Gson gsonParser = builder.create();

        // Load the data from the JSON file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            ArrayList<Trip> parsedTrips;
            parsedTrips = gsonParser.fromJson(reader, new TypeToken<ArrayList<Trip>>() {}.getType());
            for (Trip trip : parsedTrips) {
                tripList.add(trip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Set the tripList for this class
        JSONStrategy.tripList = tripList;
        // Return the results
        return tripList;
    }

    class TransportTypeDeserializer implements JsonDeserializer<TransportType> {
        @Override
        public TransportType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String transportClass = jsonObject.get("class").getAsString();
            switch (transportClass) {
                case "PrivateJet":
                    return context.deserialize(json, PrivateJet.class);
                // add more cases for other subclasses of TransportType as needed
                default:
                    throw new JsonParseException("Unknown transport class: " + transportClass);
            }
        }
    }

    class StateDeserializer implements JsonDeserializer<State> {
        @Override
        public State deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String stateClass = jsonObject.get("class").getAsString();
            switch (stateClass) {
                case "AwaitPaymentState":
                    return context.deserialize(json, AwaitPaymentState.class);
                // add more cases for other subclasses of State as needed
                default:
                    throw new JsonParseException("Unknown state class: " + stateClass);
            }
        }
    }


    public int getMaxTripID() {
        int x = 0;
        for (Trip trip : tripList) {
            int thisTripID = trip.getUniqueId();
            if (thisTripID > x) {
                x = thisTripID;
            }
        }
        return x;
    }
}