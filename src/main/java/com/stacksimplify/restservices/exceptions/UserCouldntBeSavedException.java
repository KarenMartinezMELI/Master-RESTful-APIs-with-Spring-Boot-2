package com.stacksimplify.restservices.exceptions;

public class UserCouldntBeSavedException extends Exception {
    private static final long serialVersionUID = 2L;

    public UserCouldntBeSavedException(String message) {
        super(message);
    }
}