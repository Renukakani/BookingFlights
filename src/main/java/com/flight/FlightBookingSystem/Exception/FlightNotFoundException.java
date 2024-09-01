package com.flight.FlightBookingSystem.Exception;

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(String message) {
        super(message);
    }
}
