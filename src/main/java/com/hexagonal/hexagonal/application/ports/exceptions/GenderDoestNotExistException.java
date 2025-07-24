package com.hexagonal.hexagonal.application.ports.exceptions;

public class GenderDoestNotExistException extends RuntimeException{ 
    public GenderDoestNotExistException(String message){
        super(message);
    }

}
