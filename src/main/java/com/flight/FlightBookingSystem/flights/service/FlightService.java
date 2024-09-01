package com.flight.FlightBookingSystem.flights.service;

import com.flight.FlightBookingSystem.Exception.DuplicateFlightException;
import com.flight.FlightBookingSystem.Exception.FlightNotFoundException;
import com.flight.FlightBookingSystem.flights.dto.FlightDto;
import com.flight.FlightBookingSystem.flights.dto.FlightRepository;
import com.flight.FlightBookingSystem.flights.model.Flight;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight createFlight(FlightDto flightDto){
        try {
            Flight flight = new Flight();
            System.out.println(flightDto.getFlightNumber());
            System.out.println(flightDto.getDepartureTime());
            System.out.println(flightDto.getDepartureCity());
            System.out.println(flightDto.getArrivalCity());
            System.out.println(flightDto.getSeatsAvailable());
            System.out.println(flightDto.getPricePerSeat());

            flight.setFlightNumber(flightDto.getFlightNumber());
            flight.setDepartureTime(flightDto.getDepartureTime());
            flight.setDepartureCity(flightDto.getDepartureCity());
            flight.setArrivalCity(flightDto.getArrivalCity());
            flight.setSeatsAvailable(flightDto.getSeatsAvailable());
            flight.setPricePerSeat(flightDto.getPricePerSeat());
            return flightRepository.save(flight);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateFlightException("Flight with this number already exists.");
        }
    }
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
    }
    public void updateSeatsAvailable(Long flightId, int newSeatsAvailable) {
        flightRepository.updateSeatsAvailable(flightId,newSeatsAvailable);
    }
    public Flight updateFlight(Long id, FlightDto flightDto) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
        try{
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setDepartureCity(flightDto.getDepartureCity());
        flight.setArrivalCity(flightDto.getArrivalCity());
        flight.setSeatsAvailable(flightDto.getSeatsAvailable());
        flight.setPricePerSeat(flightDto.getPricePerSeat());
        return flightRepository.save(flight);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateFlightException("Flight with this number already exists.");
        }
    }
    public void deleteFlight(Long id) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        flightRepository.delete(existingFlight);
    }

    public Page<Flight> getAllFlights(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("departureTime"));
        return flightRepository.findAll(pageable);
    }
}
