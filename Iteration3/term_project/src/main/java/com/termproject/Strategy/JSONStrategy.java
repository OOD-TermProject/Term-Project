package com.termproject.Strategy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public JSONStrategy() {
//        tripList = loadAllTrips();
    }

    /**
     * @param tripID
     */
    @Override
    void saveTrip(int tripID) {

    }

    /**
     * @param tripID
     * @return
     */
    @Override
    public Trip loadTrip(int tripID) {
        System.out.println("[JSONStrategy] Attempting to load trip #" + tripID);
        Gson gsonParser = new Gson();
        try {
            Trip thisTrip = gsonParser.fromJson(new FileReader(filePath), Trip.class);
            System.out.println(thisTrip);
            return thisTrip;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
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
        return 0;
    }

//    public ArrayList<Trip> getAllTrips() {
//        return tripList;
//    }
}