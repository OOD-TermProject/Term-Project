package com.termproject.Strategy;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.termproject.Payment.Cash;
import com.termproject.Payment.Check;
import com.termproject.Payment.CreditCard;
import com.termproject.Payment.PaymentType;
import com.termproject.People.Customer;
import com.termproject.People.NonTraveler;
import com.termproject.People.Traveler;
import com.termproject.State.*;
import com.termproject.Transport.*;
import com.termproject.Trip.Trip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class JSONStrategy extends RWStrategy {
    // Path to the JSON file of trips
    private static final String filePath = "term_project/src/main/java/com/termproject/trips.json";
    private static final ArrayList<Trip> tripList = new ArrayList<>();

    /**
     * Saves trip to disk
     *
     * @param tripToSave
     */
    @Override
    public void saveTrip(Trip tripToSave) {
        Gson objectParser = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        // Loop over each trip in the tripList
        for (Trip trip : tripList) {
            // If we find a trip with the same unique ID, delete it
            if (trip.getUniqueId() == tripToSave.getUniqueId()) {
                tripList.remove(trip);
                break;
            }
        }

        // Next add the passed-in Trip object to the ArrayList
        tripList.add(tripToSave);

        // Convert the ArrayList to a string
        String rawJSON = objectParser.toJson(tripList);

        // Write the new JSON string to the file
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(rawJSON);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * @return
     */
    public ArrayList<Trip> getAllTrips() {
        // Clear the existing list of trips
        tripList.clear();

        // Empty ArrayList to hold any trips we find in the JSON file
        ArrayList<Trip> parsedTrips = new ArrayList<>();

        // Since we have to define custom deserializers, create a GsonBuilder and register them
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(TransportType.class, new TransportTypeDeserializer());
        builder.registerTypeAdapter(State.class, new StateDeserializer());
        builder.registerTypeAdapter(Customer.class, new CustomerDeserializer());
        builder.registerTypeAdapter(PaymentType.class, new PaymentDeserializer());
        builder.registerTypeAdapter(LocalTime.class, new TimeDeserializer());
        builder.registerTypeAdapter(LocalDate.class, new DateDeserializer());
        builder.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer());
        Gson gsonParser = builder.create();

        // Load the data from the JSON file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            parsedTrips = gsonParser.fromJson(reader, new TypeToken<ArrayList<Trip>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Trip trip : parsedTrips) {
            if (!trip.isComplete()) {
                tripList.add(trip);
            }

        }
        // Return the results
        return tripList;
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

    class TransportTypeDeserializer implements JsonDeserializer<TransportType> {
        @Override
        public TransportType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String transportClass = jsonObject.get("transportName").getAsString();
            switch (transportClass) {
                case "PrivateJet":
                    return context.deserialize(json, PrivateJet.class);
                case "Helicopter":
                    return context.deserialize(json, Helicopter.class);
                case "Limousine":
                    return context.deserialize(json, Limousine.class);
                case "Yacht":
                    return context.deserialize(json, Yacht.class);
                default:
                    throw new JsonParseException("Unknown transport class: " + transportClass);
            }
        }
    }

    class StateDeserializer implements JsonDeserializer<State> {
        @Override
        public State deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String stateClass = jsonObject.get("className").getAsString();
            switch (stateClass) {
                case "AwaitPaymentState":
                    return context.deserialize(json, AwaitPaymentState.class);
                case "AwaitTravelersState":
                    return context.deserialize(json, AwaitTravelersState.class);
                case "AwaitPackagesState":
                    return context.deserialize(json, AwaitPackagesState.class);
                case "AwaitThankYouState":
                    return context.deserialize(json, AwaitThankYouState.class);
                case "ItineraryReadyState":
                    return context.deserialize(json, ItineraryReadyState.class);
                default:
                    throw new JsonParseException("Unknown state class: " + stateClass);
            }
        }
    }

    class CustomerDeserializer implements JsonDeserializer<Customer> {
        @Override
        public Customer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String customerClass = jsonObject.get("customerType").getAsString();
            switch (customerClass) {
                case "Traveler":
                    return context.deserialize(json, Traveler.class);
                case "NonTraveler":
                    return context.deserialize(json, NonTraveler.class);
                default:
                    throw new JsonParseException("Unknown person class: " + customerClass);
            }
        }
    }

    class PaymentDeserializer implements JsonDeserializer<PaymentType> {
        @Override
        public PaymentType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String customerClass = jsonObject.get("paymentType").getAsString();
            switch (customerClass) {
                case "Cash":
                    return context.deserialize(json, Cash.class);
                case "Check":
                    return context.deserialize(json, Check.class);
                case "CreditCard":
                    return context.deserialize(json, CreditCard.class);
                default:
                    throw new JsonParseException("Unknown person class: " + customerClass);
            }
        }
    }

    class TimeDeserializer implements JsonDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String timeClass = jsonObject.get("departTime").getAsString();
            return context.deserialize(json, LocalTime.class);
        }
    }

    class DateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String dateClass = jsonObject.get("departTime").getAsString();
            return context.deserialize(json, LocalDate.class);
        }
    }

    class DateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String dateTimeClass = jsonObject.get("departDateTime").getAsString();
            return context.deserialize(json, LocalDateTime.class);
        }
    }
}