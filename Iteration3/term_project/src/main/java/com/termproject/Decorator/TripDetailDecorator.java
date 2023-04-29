package com.termproject.Decorator;

import com.termproject.Trip.Package;
import com.termproject.Trip.Reservation;
import com.termproject.Trip.Trip;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class TripDetailDecorator extends ItineraryDecorator {

	transient Itinerary itinerary;
	transient Trip thisTrip;
	transient LocalDateTime departDateTime;
	transient LocalDateTime arrivalDateTime;

	private static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	public TripDetailDecorator(Itinerary itinerary, Trip trip) {
		
		this.itinerary = itinerary;
		this.thisTrip = trip;
		ArrayList<Reservation> reservations = trip.getReservations();

		// Find the arrival/depart times of the first reservation
		LocalDateTime earlyDepart = LocalDateTime.parse(reservations.get(0).getDepartingOn());
		LocalDateTime earlyArrive = LocalDateTime.parse(reservations.get(0).getArrivingOn());
		// Loop through each reservation
		for (Reservation rsv : reservations) {
			// Get the arrival/depart times for this reservation
			LocalDateTime thisDepart = LocalDateTime.parse(rsv.getDepartingOn());
			LocalDateTime thisArrive = LocalDateTime.parse(rsv.getArrivingOn());
			// Find the earliest one
			if (thisDepart.isBefore(earlyDepart)) {
				earlyDepart = thisDepart;
			}
			if (thisArrive.isBefore(earlyArrive)) {
				earlyArrive = thisArrive;
			}
		}

		// Update the arrival/depart times to be shown on the document
		this.departDateTime = earlyDepart;
		this.arrivalDateTime = earlyArrive;
	}
	
	public String printTripDetails() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);
		
		String result = "Trip Details, beginning " + departDateTime.toLocalDate().format(dateFormatter) + " at " + departDateTime.toLocalTime().format(timeFormatter) + " and ending on " +
				arrivalDateTime.toLocalDate().format(dateFormatter) + " at " + arrivalDateTime.toLocalTime().format(timeFormatter) + "\n\n";
		
		int count = 1;	//For numbering the list from 1

		ArrayList<Package> allPackages = new ArrayList<>();
		for (Reservation rsv : thisTrip.getReservations()) {
			allPackages.addAll(rsv.getPackages());
		}
		
		result += "This trip consists of " + this.thisTrip.getReservations().size() + " reservation";
		if (this.thisTrip.getReservations().size() > 1) {
			result += "s";
		}
		result += ":\n\n";
		for (Reservation rsv : this.thisTrip.getReservations()) {
			String thisDepart = LocalDateTime.parse(rsv.getDepartingOn()).toLocalDate().format(dateFormatter);
			result += "\tReservation #" + count + " departing on " + thisDepart + " with " + rsv.getPackages().size() + " package";
			if (rsv.getPackages().size() > 1) {
				result += "s";
			}
			result += ":\n";
			for (Package pkg : rsv.getPackages()) {
				result += "\t\t" + pkg.getItineraryFormat() + "\n";
			}
			result += "\n";
			count++;
		}
		
		return result;
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + printTripDetails() + "\n";
		
	}
	
}
