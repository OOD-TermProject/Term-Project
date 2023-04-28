package com.termproject.Payment;

public class Cash extends PaymentType {

    private final String paymentType = "Cash";

    @Override
    public String toString() {
        return "Cash";
    }
}
