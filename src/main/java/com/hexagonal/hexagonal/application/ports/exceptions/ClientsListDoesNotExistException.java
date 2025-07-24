package com.hexagonal.hexagonal.application.ports.exceptions;

public class ClientsListDoesNotExistException extends RuntimeException{
    public ClientsListDoesNotExistException(String message) {
        super(message);
    }

}
