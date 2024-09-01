package com.flight.FlightBookingSystem.Exception;

public class DuplicateFlightException extends RuntimeException{
    public DuplicateFlightException(String message) {
        super(message);
    }
}
