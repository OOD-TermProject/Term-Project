package com.termproject.Payment;

public class Check extends PaymentType {

    private final String paymentType = "Check";
    public int getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
    }

    private int checkNumber = -999;

    @Override
    public String toString() {
        return "Check";
    }
}
