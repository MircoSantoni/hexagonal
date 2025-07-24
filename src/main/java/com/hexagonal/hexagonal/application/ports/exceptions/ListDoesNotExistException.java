package com.hexagonal.hexagonal.application.ports.exceptions;

public class ListDoesNotExistException extends RuntimeException{
    public ListDoesNotExistException(String message) {
        super(message);
    }

}
