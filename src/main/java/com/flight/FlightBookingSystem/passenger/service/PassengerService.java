package com.flight.FlightBookingSystem.passenger.service;

import com.flight.FlightBookingSystem.Exception.DuplicateFlightException;
import com.flight.FlightBookingSystem.Exception.PassengerNotFoundException;
import com.flight.FlightBookingSystem.passenger.dto.PassengerDto;
import com.flight.FlightBookingSystem.passenger.dto.PassengerRepository;
import com.flight.FlightBookingSystem.passenger.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger createPassenger(PassengerDto passengerDto){
        try{
        Passenger passenger=new Passenger();
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setPassportNumber(passengerDto.getPassportNumber());

        return passengerRepository.save(passenger);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateFlightException("Passport Number already exists.");
        }
    }

    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + id));
    }

    // Update passenger details
    public Passenger updatePassenger(Long id, PassengerDto passengerDto) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + id));
        try{
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setPassportNumber(passengerDto.getPassportNumber());
        return passengerRepository.save(passenger);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateFlightException("Passport Number already exists.");
        }
    }
    // Delete passenger by ID
    public void deletePassenger(Long id) {
        Passenger existingPassenger = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + id));

        passengerRepository.delete(existingPassenger);
    }

    // Get all passengers with pagination and sorting
    public Page<Passenger> getAllPassengers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName")); // Assuming sorting by lastName
        return passengerRepository.findAll(pageable);
    }
}
