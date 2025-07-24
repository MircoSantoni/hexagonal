package com.hexagonal.hexagonal.infrastructure.exceptions;

public class EmailAlreadyInUseException extends RuntimeException{ 
    public EmailAlreadyInUseException(String message) {
        super(message);
    }

}
