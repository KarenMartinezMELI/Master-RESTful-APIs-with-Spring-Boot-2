package com.stacksimplify.restservices.exceptions;

public class OrderNotFoundException extends Exception{
    private static final long serialVersionUID = 5L;

    public OrderNotFoundException(String message) {
        super(message);
    }
}