package com.flight.FlightBookingSystem.Exception;

public class SeatsNotAvailableException extends RuntimeException{
    public SeatsNotAvailableException(String message) {
        super(message);
    }
}
