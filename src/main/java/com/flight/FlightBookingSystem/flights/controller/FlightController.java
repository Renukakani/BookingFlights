package com.flight.FlightBookingSystem.flights.controller;

import com.flight.FlightBookingSystem.flights.dto.FlightDto;
import com.flight.FlightBookingSystem.flights.model.Flight;
import com.flight.FlightBookingSystem.flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDto dto) {
        Flight createdFlight = flightService.createFlight(dto);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable("id") Long id){
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    @GetMapping
    public ResponseEntity<Page<Flight>> getAllFlights(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Flight> flights = flightService.getAllFlights(page, size);
        return ResponseEntity.ok(flights);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("id") Long id, @RequestBody FlightDto dto){
        System.out.println("-----"+dto.getFlightNumber());
        Flight flight=flightService.updateFlight(id,dto);
        return ResponseEntity.ok(flight);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable("id") Long id){
        flightService.deleteFlight(id);
       // return ResponseEntity.noContent().build();
        return ResponseEntity.ok("Deleted Successfully");
    }
}
