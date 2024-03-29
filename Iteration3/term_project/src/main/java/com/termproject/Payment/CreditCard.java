package com.termproject.Payment;

public class CreditCard extends PaymentType {

    private final String paymentType = "CreditCard";
    private long cardNumber = -999;
    private String expDate = null;

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "Credit Card";
    }
}
