package com.termproject.Decorator;

public class BillingDecorator extends ItineraryDecorator {

	Itinerary itinerary;
	
	float totalPrice;
	
	boolean isPaidInFull;
	String paidBy;
	String paymentMethod;
	float amountPaid;
	String cardNumber;
	String checkNumber;
	String expDate;
	
	String[] transport;
	String[] departFrom;
	String[] destination;
	String[] departDate;
	String[] departTime;
	String[] arrivalDate;
	String[] arrivalTime;
	float[] price;
	
	public BillingDecorator(Itinerary itinerary) {
		
		this.itinerary = itinerary;
		
	}
	
	public String PrintTotal(float totalPrice) {
		
		return "Total: " + Float.toString(totalPrice) + "\n";
		
	}
	
	public String PrintPayment(boolean isPaidInFull, String paidBy, String paymentMethod, float amountPaid) {
		
		String result = "";
		
		if(isPaidInFull == true) {
			result += "Paid in full by " + paidBy + " using " + paymentMethod + "\n" ;
		}
		else {
			result += "Partial payment made by " + paidBy + " using " + paymentMethod + "\n";
		}
		
		result += "Amount: " + amountPaid + "\n";
		
		if(paymentMethod == "Credit Card") {
			result += "Card number: \nExpiration date: \n";
		}
		else if(paymentMethod == "Check") {
			result += "Check number: \n";
		}
		
		return result;
	}
	
	public String PrintBillingDetails(String[] transport, String[] departFrom, String[] destination,
			String[] departDate, String[] departTime, String[] arrivalDate, String[] arrivalTime, float[] price) {
		
		String result = "Billing Details: \n";
		
		for (int i = 0; i < transport.length; i++) {
			result += "-	" + transport[i] + " from " + departFrom[i]  + " to " + destination[i] +
					" on " + departDate[i] + " at " + departTime[i] + ", arriving " + arrivalDate[i] + 
					" at " + arrivalTime[i] + "\n$" + price[i] + "\n";
		}
		return result;
	}
	
	@Override
	public String getItinerary() {
		return itinerary.getItinerary() + "Billing: \n" + PrintTotal(totalPrice) + 
				PrintPayment(isPaidInFull, paidBy, paymentMethod, amountPaid) + 
				PrintBillingDetails(transport, departFrom, destination, departDate, departTime, arrivalDate, arrivalTime, price);
	}
	
}
