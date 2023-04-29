package com.termproject.Payment;

public class Bill {
    private float totalPrice;
    private boolean isPaidInFull;
    private Payment payment;

    public Bill(float totalPrice) {
        this.totalPrice = totalPrice;
        this.isPaidInFull = false;
        this.payment = null;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaidInFull() {
        return isPaidInFull;
    }

    public void setPaidInFull(boolean paidInFull) {
        isPaidInFull = paidInFull;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
