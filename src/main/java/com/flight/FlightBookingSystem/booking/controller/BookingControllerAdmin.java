package com.flight.FlightBookingSystem.booking.controller;

import com.flight.FlightBookingSystem.booking.dto.BookingDto;
import com.flight.FlightBookingSystem.booking.model.Booking;
import com.flight.FlightBookingSystem.booking.service.BookingService;
import com.flight.FlightBookingSystem.flights.model.Flight;
import com.flight.FlightBookingSystem.flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/bookings")
public class BookingControllerAdmin {
    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public ResponseEntity<Page<Booking>> getAllBooking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Booking> booking = bookingService.getAllBookings(page,size);
        return ResponseEntity.ok(booking);
    }
}
