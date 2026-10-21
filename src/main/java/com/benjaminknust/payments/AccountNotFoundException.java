package com.benjaminknust.payments;

public class AccountNotFoundException extends ApiException {
    public AccountNotFoundException(Long id) {
        super(404, "Account not found: " + id);
    }
}