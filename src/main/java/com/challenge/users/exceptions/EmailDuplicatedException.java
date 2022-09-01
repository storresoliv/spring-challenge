package com.challenge.users.exceptions;

public class EmailDuplicatedException extends RuntimeException {
    public EmailDuplicatedException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Email already exits";
    }
}
