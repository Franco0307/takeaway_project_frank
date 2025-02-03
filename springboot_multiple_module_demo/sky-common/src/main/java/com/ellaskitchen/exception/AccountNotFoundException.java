package com.ellaskitchen.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
