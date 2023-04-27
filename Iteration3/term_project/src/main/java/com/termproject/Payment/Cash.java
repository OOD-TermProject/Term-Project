package com.termproject.Payment;

public class Cash extends PaymentType {

    private final String paymentName = "Cash";

    @Override
    public String toString() {
        return "Cash";
    }
}
