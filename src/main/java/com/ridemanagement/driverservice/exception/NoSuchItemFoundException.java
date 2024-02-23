package com.ridemanagement.driverservice.exception;

public class NoSuchItemFoundException extends RuntimeException{

    public NoSuchItemFoundException(String message){
        super(message);
    }
}
