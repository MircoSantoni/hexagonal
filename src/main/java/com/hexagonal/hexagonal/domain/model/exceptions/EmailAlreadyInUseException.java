package com.hexagonal.hexagonal.domain.model.exceptions;

public class EmailAlreadyInUseException extends RuntimeException{ 
    public EmailAlreadyInUseException(String message) {
        super(message);
    }

}
