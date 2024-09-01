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
@RequestMapping("/api/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @PostMapping
    public ResponseEntity<Passenger> createFlight(@RequestBody PassengerDto passenger) {
        Passenger createdPassenger = passengerService.createPassenger(passenger);
        return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable("id") Long id){
        Passenger passenger = passengerService.getPassengerById(id);
        return ResponseEntity.ok(passenger);
    }

    @GetMapping()
    public ResponseEntity<Page<Passenger>> getAllPassengers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Passenger> passenger = passengerService.getAllPassengers(page,size);
        return ResponseEntity.ok(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable("id") Long id,@RequestBody PassengerDto dto){
        Passenger passenger=passengerService.updatePassenger(id,dto);
        return ResponseEntity.ok(passenger);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable("id") Long id){
        passengerService.deletePassenger(id);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
