package com.stacksimplify.restservices.exceptions;

public class UserCouldntBeSavedException extends Exception {
    private static final long serialVersionUID = 3L;

    public UserCouldntBeSavedException(String message) {
        super(message);
    }
}