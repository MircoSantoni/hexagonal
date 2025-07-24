package com.hexagonal.hexagonal.infrastructure.exceptions;

public class EmptyResourceException extends RuntimeException {
    public EmptyResourceException(String message) {
        super(message);
    }

}
