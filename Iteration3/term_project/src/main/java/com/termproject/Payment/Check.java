package com.termproject.Payment;

public class Check extends PaymentType {

    private final String paymentType = "Check";
    private int checkNumber = -999;

    public int getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Override
    public String toString() {
        return "Check";
    }
}
