package com.termproject.Decorator;

import com.termproject.Payment.Check;
import com.termproject.Payment.CreditCard;
import com.termproject.Trip.Package;
import com.termproject.Trip.Reservation;
import com.termproject.Trip.Trip;

public class BillingDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	Trip thisTrip;
	
	public BillingDecorator(Itinerary itinerary, Trip trip) {
		this.itinerary = itinerary;
		this.thisTrip = trip;
	}
	
	@Override
	public String getItinerary() {
		String completedString = itinerary.getItinerary();
		completedString += "\nBilling:\n";
		completedString += firstSection();

		completedString += "\n\tBilling Details:";
		for (Reservation rsv : thisTrip.getReservations()) {
			for (Package pkg : rsv.getPackages()) {
				completedString += String.format("\n\t\t%s -- $%.2f\n\n", pkg.getItineraryFormat(), pkg.getPrice());
			}
		}
		return completedString;
	}

	private String firstSection() {
		String paymentMethod = thisTrip.getBill().getPayment().getPaymentMethod().toString().toLowerCase();

		String completedString = String.format("\n\tTotal: $%.2f", thisTrip.getBill().getTotalPrice());
		completedString += String.format("\n\tPaid in full by %s using %s", thisTrip.getBill().getPayment().getPaidBy(), paymentMethod);
		switch (paymentMethod) {
			case "credit card":
				CreditCard thisCC = (CreditCard) thisTrip.getBill().getPayment().getPaymentMethod();
				completedString += String.format("\t\tCard number: %s\n", thisCC.getCardNumber());
				completedString += String.format("\t\tExpiration date: %s\n", thisCC.getExpDate());
				break;
			case "check":
				Check thisCheck = (Check) thisTrip.getBill().getPayment().getPaymentMethod();
				completedString += String.format("\t\tCheck number: %s\n", thisCheck.getCheckNumber());
				break;
			default:
				break;
		}
		completedString += String.format("\t\tAmount: %.2f\n", thisTrip.getBill().getPayment().getAmountPaid());

		return completedString;
	}
	
}
