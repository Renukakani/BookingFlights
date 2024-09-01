package com.flight.FlightBookingSystem.passenger.controller;

import com.flight.FlightBookingSystem.flights.model.Flight;
import com.flight.FlightBookingSystem.flights.service.FlightService;
import com.flight.FlightBookingSystem.passenger.dto.PassengerDto;
import com.flight.FlightBookingSystem.passenger.model.Passenger;
import com.flight.FlightBookingSystem.passenger.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/passenger")
public class PassengerControllerAdmin {
    @Autowired
    private PassengerService passengerService;

    @GetMapping()
    public ResponseEntity<Page<Passenger>> getAllPassengers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Passenger> passenger = passengerService.getAllPassengers(page,size);
        return ResponseEntity.ok(passenger);
    }


}
