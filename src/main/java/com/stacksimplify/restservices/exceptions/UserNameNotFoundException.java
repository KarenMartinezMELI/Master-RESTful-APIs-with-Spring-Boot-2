package com.stacksimplify.restservices.exceptions;


public class UserNameNotFoundException  extends Exception {
    private static final long serialVersionUID = 4L;

    public UserNameNotFoundException(String message) {
        super(message);
    }
}