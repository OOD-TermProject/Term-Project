package com.termproject.Decorator;

import com.termproject.Trip.Package;
import com.termproject.Trip.Reservation;
import com.termproject.Trip.Trip;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class TripDetailDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	Trip thisTrip;
	Date departDate;
	String departTime;
	Date arrivalDate;
	String arrivalTime;

	private static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	public TripDetailDecorator(Itinerary itinerary, Trip trip) {
		
		this.itinerary = itinerary;
		this.thisTrip = trip;
		ArrayList<Reservation> reservations = trip.getReservations();
		try {
			this.departDate = format.parse(reservations.get(0).getDepartingOn());
			this.arrivalDate = format.parse(reservations.get(0).getArrivingOn());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Reservation rsv : reservations) {
			try {
				Date temp1 = format.parse(rsv.getArrivingOn());
				Date temp2 = format.parse(rsv.getArrivingOn());
				if (this.departDate.compareTo(temp1) < 0) {
					this.departDate = temp1;
					LocalTime startTime = rsv.getPackages().get(0).getDepartTime();
					for (Package pkg : rsv.getPackages()) {
						if (pkg.getDepartTime().isBefore(startTime)) {
							startTime = pkg.getDepartTime();
						}

					}
					this.departTime = startTime.toString();
				}
				if (this.arrivalDate.compareTo(temp2) < 0) {
					this.arrivalDate = temp2;
					LocalTime endTime = rsv.getPackages().get(0).getArrivalTime();
					for (Package pkg : rsv.getPackages()) {
						if (pkg.getArrivalTime().isAfter(endTime)) {
							endTime = pkg.getArrivalTime();
						}
						if (pkg.getArrivalTime().isAfter(endTime)) {
							endTime = pkg.getArrivalTime();
						}
						this.arrivalTime = endTime.toString();
					}
				}
			} catch (Exception f) {
				f.printStackTrace();
			}
		}
	}
	
	public String printTripDetails() {
		
		String result = "Trip Details, beginning " + departDate.toString() + " at " + departTime + " and ending on " +
				arrivalDate.toString() + " at " + arrivalTime + "\n";
		
		int count = 1;	//For numbering the list from 1

		ArrayList<Package> allPackages = new ArrayList<>();
		for (Reservation rsv : thisTrip.getReservations()) {
			allPackages.addAll(rsv.getPackages());
		}
		
		result += "This trip consists of " + this.thisTrip.getReservations().size() + " reservations:\n";
		for (Reservation rsv : this.thisTrip.getReservations()) {
			result += "\tReservation #" + count + " departing on " + rsv.getDepartingOn() + " with " + rsv.getPackages().size() + " packages:";
			for (Package pkg : rsv.getPackages()) {
				result += "\t\t" + pkg.getItineraryFormat();
			}
		}
		
		return result;
	}
	
	@Override
	public String getItinerary() {
		
		return itinerary.getItinerary() + printTripDetails() + "\n";
		
	}
	
}
