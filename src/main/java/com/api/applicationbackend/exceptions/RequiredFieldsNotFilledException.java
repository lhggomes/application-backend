package com.api.applicationbackend.exceptions;

public class RequiredFieldsNotFilledException extends Exception {

    public RequiredFieldsNotFilledException(String message) {
        super(message);
    }
}
