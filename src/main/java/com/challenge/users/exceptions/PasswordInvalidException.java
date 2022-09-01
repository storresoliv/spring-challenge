package com.challenge.users.exceptions;

public class PasswordInvalidException extends InvalidParameterException {
    public PasswordInvalidException(Exception ex) {
        super(ex);
    }

    @Override
    public String getMessage() {
        return "password parameter is invalid. Password must contain one upper char, at least two lower chars and at least two numbers";
    }
}