package com.termproject.State;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.termproject.Singleton.PackageList;
import com.termproject.Trip.Package;
import com.termproject.Trip.Reservation;
import com.termproject.Trip.Trip;

public class AwaitPackagesState implements State {

	private static final PackageList packages = PackageList.getInstance();
	static ArrayList<Package> listOfPackages = packages.getPackageList();

	public void setThisTrip(Trip thisTrip) {
		this.thisTrip = thisTrip;
	}

	private transient Trip thisTrip;
	private ArrayList<Reservation> reservations;
	public final String className = "AwaitPackagesState";
	private static final String futureVerb = "Add packages to a reservation";
	private static final String pastVerb = "Done adding packages";
	private static Scanner scan = null;
	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	public AwaitPackagesState(Trip currentTrip) {
		this.reservations = new ArrayList<>();
		this.thisTrip = currentTrip;
	}

	private Reservation newReservation() {
		System.out.println("\nCreating a new reservation.");
		System.out.print("Please enter the departure date for this reservation (MM/dd/yyyy): ");
		String reservationStart = scan.nextLine();
		LocalDate startDate = null;
		LocalDate endDate = null;
		try {
			startDate = LocalDate.parse(reservationStart, format);
		} catch (Exception e) {
			System.out.println("Dates must be in the format mm/dd/yyyy");
			return null;
		}
		System.out.print("Please enter the arrival date for this reservation (MM/dd/yyyy): ");
		String reservationEnd = scan.nextLine();
		try {
			endDate = LocalDate.parse(reservationEnd, format);
		} catch (Exception e) {
			System.out.println("Dates must be in the format mm/dd/yyyy");
			return null;
		}
		reservationStart = startDate.toString();
		reservationEnd = endDate.toString();
		Reservation newReservation = new Reservation(reservationStart, reservationEnd);
		reservations.add(newReservation);
		return newReservation;
	}

	private Reservation chooseReservation() {
		Reservation currentReservation = null;
		if (reservations.size() == 0) {
			currentReservation = newReservation();
			if (currentReservation == null) {
				return null;
			}
		} else {
			System.out.println("Current trip reservations:\n");
			for (int j = 0; j < reservations.size(); j++) {
				Reservation tempReservation = reservations.get(j);
				System.out.println("\t" + (j + 1) + ". " + tempReservation);
			}
			System.out.print("\nSelect a reservation or type 'new' to create a new Reservation: ");
			if (scan.hasNextInt()) {
				int reservationInput = scan.nextInt();
				scan.nextLine();
				if (reservationInput <= reservations.size()) {
					currentReservation = reservations.get(reservationInput - 1);
				}

			} else if (scan.nextLine().equalsIgnoreCase("new")) {
				currentReservation = newReservation();
			}
		}
		return currentReservation;
	}

	private void addPackages(Reservation rsv) {
		System.out.println("Please select a package from the following list:\n");
		for (int i = 0; i < listOfPackages.size(); i++) {
			Package tempPackage = listOfPackages.get(i);
			System.out.println("\t" + (i + 1) + ". " + tempPackage);
		}
		System.out.print("\nChoose a package: ");
		if (scan.hasNextInt()) {
			int packageInput = scan.nextInt();
			scan.nextLine();
			if (packageInput <= (listOfPackages.size())) {
				rsv.addPackage(listOfPackages.get(packageInput - 1));
				return;
			}
		}
		System.out.println("Invalid selection. Please try again.");
	}

	@Override
	public State advanceState() {
		// Do not let user advance the state if they haven't added any reservations
		if (this.reservations.size() < 1) {
			System.out.println("You must add at least one reservation before moving on.");
			return this;
		}
		// Do not let user advance the state if all reservations are empty
		int packageCount = 0;
		for (Reservation reservation : reservations) {
			packageCount += reservation.getPackages().size();
		}
		if (packageCount < 1) {
			System.out.println("You must add at least one package before moving on.");
			return this;
		}

		// If the checks pass, add the reservations and move to the next state
		for (Reservation rsv : reservations) {
			this.thisTrip.addReservation(rsv);
		}
		return new AwaitPaymentState(thisTrip);
	}

	/**
	 * @return
	 */
	@Override
	public String getStateInfo() {
		if ((this.reservations != null) && (this.reservations.size() > 0)) {
			String completedString = String.format("%s reservation", this.reservations.size());
			if (this.reservations.size() > 1) {
				completedString += "s";
			}
			completedString += ":\n";
			for (Reservation reservation : reservations) {
				completedString += "\t" + reservation + "\n";
				for (Package pkg : reservation.getPackages()) {
					completedString += "\t\t" + pkg + "\n";
				}
				completedString += "\n";
			}
			return completedString;
		} else {
			return "No reservations yet!\n";
		}
	}

	@Override
	public String toString() {
		return "Awaiting reservations and packages";
	}

	@Override
	public String getFutureVerb() {
		if ((reservations == null) || (reservations.size() < 1)) {
			return "Create a new reservation";
		}

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
		this.scan = scanner;
		if (reservations == null) {
			reservations = new ArrayList<>();
		}
		Reservation thisReservation = chooseReservation();
		if (thisReservation == null) {
			return;
		}
		addPackages(thisReservation);
	}

}
