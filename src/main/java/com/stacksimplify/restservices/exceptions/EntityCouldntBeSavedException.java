package com.stacksimplify.restservices.exceptions;

public class EntityCouldntBeSavedException extends Exception {
    private static final long serialVersionUID = 3L;

    public EntityCouldntBeSavedException(String message, String entity) {
        super("Entity: "+entity+" - "+message);
    }
}