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
@RequestMapping("/user/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable("id") Long id){
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping()
    public ResponseEntity<Page<Booking>> getAllBooking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Booking> booking = bookingService.getAllBookings(page,size);
        return ResponseEntity.ok(booking);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
