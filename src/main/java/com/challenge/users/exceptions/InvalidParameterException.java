package com.challenge.users.exceptions;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(Exception ex) {
        super(ex);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
