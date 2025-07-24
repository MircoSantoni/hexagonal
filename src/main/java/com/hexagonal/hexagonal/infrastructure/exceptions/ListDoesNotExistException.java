package com.hexagonal.hexagonal.infrastructure.exceptions;

public class ListDoesNotExistException extends RuntimeException{
    public ListDoesNotExistException(String message) {
        super(message);
    }

}
