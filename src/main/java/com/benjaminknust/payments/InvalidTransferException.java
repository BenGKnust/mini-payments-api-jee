package com.benjaminknust.payments;

public class InvalidTransferException extends ApiException {
    public InvalidTransferException(String message) {
        super(400, message);
    }
}