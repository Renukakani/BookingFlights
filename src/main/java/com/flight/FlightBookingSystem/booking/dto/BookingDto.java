package com.flight.FlightBookingSystem.booking.dto;

import com.flight.FlightBookingSystem.BookingStatus;
import com.flight.FlightBookingSystem.booking.model.Booking;
import com.flight.FlightBookingSystem.flights.model.Flight;
import com.flight.FlightBookingSystem.passenger.model.Passenger;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDto {
    private LocalDateTime bookingDate;
    private BigDecimal totalAmount;
    private Flight flight;
    private Passenger passenger;
    private BookingStatus status;

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
