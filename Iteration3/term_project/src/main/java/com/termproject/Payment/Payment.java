package com.termproject.Payment;

import com.termproject.People.Customer;

public class Payment {

    private float amountPaid;
    private Customer paidBy;
    private PaymentType paymentMethod;

    public Payment() {
        this.amountPaid = 0.0f;
        this.paidBy = null;
        this.paymentMethod = null;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Customer getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Customer paidBy) {
        this.paidBy = paidBy;
    }

    public PaymentType getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentType pmthd) {
        this.paymentMethod = pmthd;
    }
}
