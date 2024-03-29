package com.termproject.State;

import com.termproject.Payment.*;
import com.termproject.People.Customer;
import com.termproject.People.NonTraveler;
import com.termproject.Trip.Package;
import com.termproject.Trip.Reservation;
import com.termproject.Trip.Trip;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class AwaitPaymentState implements State {

    private static Scanner scan = null;
    public final String className = "AwaitPaymentState";
    private final String pastVerb = "Done with payment info";
    private transient Trip thisTrip;
    private final Bill bill;
    private String futureVerb = "Add payment info to trip";
    private boolean readyForPayment = false;
    private boolean paymentVerified = false;
    public AwaitPaymentState(Trip currentTrip) {
        thisTrip = currentTrip;

        // Get the total price for all packages in all reservations
        float totalPrice = 0.0f;
        for (Reservation rsv : thisTrip.getReservations()) {
            for (Package pkg : rsv.getPackages()) {
                totalPrice += pkg.getPrice();
            }
        }

        // Use the total price to create the new Bill object
        this.bill = new Bill(totalPrice);
    }

    public void setThisTrip(Trip thisTrip) {
        this.thisTrip = thisTrip;
    }

    @Override
    public State advanceState() {
        // Do not let user advance the state if they haven't added payment info
        if (this.bill == null) {
            System.out.println("You must add at payment info before moving on.");
            return this;
        }
        if (this.bill.getPayment() == null) {
            System.out.println("You must enter payment info before moving on.");
            return this;
        }
        if (!paymentVerified) {
            System.out.println("You must complete payment verification before proceeding.");
            return this;
        }
        thisTrip.setBill(bill);
        return new AwaitThankYouState(thisTrip);
    }

    /**
     * @return
     */
    @Override
    public String getStateInfo() {
        String completedString = "Current bill:\n";
        completedString += "\tTotal price: $" + bill.getTotalPrice() + "\n";
        completedString += "\tPaid in full: ";
        if (bill.isPaidInFull()) {
            completedString += "YES\n";
        } else {
            completedString += "NO\n";
        }
        completedString += "\tPayment info: ";
        if (bill.getPayment() == null) {
            completedString += "No payment info added\n";
        } else {
            completedString += "\n\t\tPayer: ";
            if (bill.getPayment().getPaidBy() == null) {
                completedString += "not added\n";
            } else {
                completedString += bill.getPayment().getPaidBy().toString() + "\n";
            }
            completedString += "\t\tPayment method: ";
            if (bill.getPayment().getPaymentMethod() == null) {
                completedString += "not added\n";
            } else {
                completedString += bill.getPayment().getPaymentMethod().toString().toLowerCase();
            }
            completedString += "\n\t\tAmount paid: $" + String.format("%.2f", bill.getPayment().getAmountPaid()) + "\n";
        }

        return completedString;
    }

    @Override
    public String toString() {
        return "Awaiting payment";
    }

    @Override
    public String getFutureVerb() {
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
        scan = scanner;
        if (bill.getPayment() == null) {
            bill.setPayment(new Payment());
        }
        if (paymentVerified) {
            System.out.println("Payment already verified!");
            return;
        }

        if (bill.isPaidInFull()) {
            verifyPayment();
            return;
        }
        if (readyForPayment) {
            updateAmountPaid();
        } else {
            updatePayment();
        }

    }

    private void verifyPayment() {
        System.out.println("Verifying payment with payment processor...");
        for (int k = 0; k < 5; k++) {
            System.out.print(".");
            try {
                sleep(1200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n");
        System.out.println("Payment verified!");
        paymentVerified = true;
    }

    private void updateAmountPaid() {
        float amountPaid = bill.getPayment().getAmountPaid();
        float totalBill = bill.getTotalPrice();
        float amountRemaining = totalBill - amountPaid;
        System.out.println("Current amount paid: $" + String.format("%.2f", amountPaid) + " of $" + String.format("%.2f", totalBill));
        System.out.println("Remaining balance: $" + String.format("%.2f", amountRemaining));
        System.out.print("\nEnter amount to be applied to balance: ");
        float amountEntered = 0.0f;
        if (scan.hasNextInt()) {
            int integerAmountEntered = scan.nextInt();
            scan.nextLine();
            amountEntered = (float) integerAmountEntered;
        } else if (scan.hasNextFloat()) {
            amountEntered = scan.nextFloat();
            scan.nextLine();
        }

        // Update the payment info in the bill
        bill.getPayment().setAmountPaid(amountPaid + amountEntered);
        amountPaid = bill.getPayment().getAmountPaid();

        // Check whether the bill is complete
        if (amountPaid >= totalBill) {
            System.out.println("Bill paid in full!");
            bill.setPaidInFull(true);
            futureVerb = "Verify payment is complete";
        }
        if (amountPaid > totalBill) {
            System.out.println("Bill overpaid. A balance of $" + String.format("%.2f", (amountPaid - totalBill)) + " will be returned via " + bill.getPayment().getPaymentMethod().toString().toLowerCase());
            bill.getPayment().setAmountPaid(totalBill);
        }

    }

    private void updatePayment() {
        if (bill.getPayment().getPaidBy() == null) {
            updatePayerName();
            futureVerb = "Update payment method";
        }

        if (bill.getPayment().getPaymentMethod() == null) {
            updatePaymentMethod();
        } else {
            switch (bill.getPayment().getPaymentMethod().toString()) {
                case "Check":
                    updateCheckInfo((Check) bill.getPayment().getPaymentMethod());
                    break;
                case "Credit Card":
                    updateCreditCardInfo((CreditCard) bill.getPayment().getPaymentMethod());
                    break;
                default:
                    break;
            }
            futureVerb = "Enter amount paid";
            readyForPayment = true;
        }

    }

    private void updateCreditCardInfo(CreditCard card) {
        if (card.getCardNumber() < 0) {
            // Get credit card number
            int cardInt;
            long cardLong;
            while (true) {
                System.out.print("Enter credit card number: ");
                if (scan.hasNextInt()) {
                    cardInt = scan.nextInt();
                    scan.nextLine();
                    card.setCardNumber(cardInt);
                    break;
                } else if (scan.hasNextLong()) {
                    cardLong = scan.nextLong();
                    scan.nextLine();
                    card.setCardNumber(cardLong);
                    break;
                } else {
                    scan.nextLine();
                    System.out.println("Invalid entry. Please only use numbers for the credit card number.");
                }
            }
        }

        if (card.getExpDate() == null) {
            // Get expiration date
            System.out.print("Enter card's expiration date: ");
            String expDate = scan.nextLine();
            card.setExpDate(expDate);
        }
    }

    private void updateCheckInfo(Check check) {
        while (check.getCheckNumber() < 0) {
            System.out.print("Enter check number (numbers only): ");
            if (scan.hasNextInt()) {
                int checkNum = scan.nextInt();
                scan.nextLine();
                check.setCheckNumber(checkNum);
                bill.getPayment().setPaymentMethod(check);
                return;
            } else {
                scan.nextLine();
                System.out.println("Invalid entry. Please only use numbers for the check number.");
            }
        }
    }

    private void updatePayerName() {
        System.out.print("Enter the payer's name: ");
        String payerName = scan.nextLine();
        Customer payer = new NonTraveler();
        payer.setName(payerName);
        bill.getPayment().setPaidBy(payer);
        System.out.println("Payer's name updated!");
    }

    private void updatePaymentMethod() {
        System.out.print("Select a payment method to be used:\n");
        System.out.println("\t1. Cash");
        System.out.println("\t2. Check");
        System.out.println("\t3. Credit Card");
        System.out.print("Select an option: ");

        if (scan.hasNextInt()) {
            int selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
                case 1:
                    bill.getPayment().setPaymentMethod(new Cash());
                    readyForPayment = true;
                    futureVerb = "Enter amount paid";
                    break;
                case 2:
                    bill.getPayment().setPaymentMethod(new Check());
                    futureVerb = "Add check number";
                    break;
                case 3:
                    bill.getPayment().setPaymentMethod(new CreditCard());
                    futureVerb = "Add credit card details";
                    break;
            }
        } else {
            System.out.println("Invalid entry. Please try again.");
        }
    }
}
