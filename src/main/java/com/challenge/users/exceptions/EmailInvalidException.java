package com.challenge.users.exceptions;

public class EmailInvalidException extends InvalidParameterException {
    public EmailInvalidException(Exception ex) {
        super(ex);
    }

    @Override
    public String getMessage() {
        return "email format is invalid";
    }
}
