package com.benjaminknust.payments;

public class InsufficientFundsException extends ApiException {
    public InsufficientFundsException() {
        super(422, "Insufficient funds");
    }
}
