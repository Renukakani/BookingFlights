package com.flight.FlightBookingSystem.booking.service;

import com.flight.FlightBookingSystem.BookingStatus;
import com.flight.FlightBookingSystem.Exception.BookingNotFoundException;
import com.flight.FlightBookingSystem.Exception.FlightNotFoundException;
import com.flight.FlightBookingSystem.Exception.PassengerNotFoundException;
import com.flight.FlightBookingSystem.Exception.SeatsNotAvailableException;
import com.flight.FlightBookingSystem.booking.dto.BookingDto;
import com.flight.FlightBookingSystem.booking.dto.BookingRepository;
import com.flight.FlightBookingSystem.booking.model.Booking;
import com.flight.FlightBookingSystem.flights.dto.FlightRepository;
import com.flight.FlightBookingSystem.flights.model.Flight;
import com.flight.FlightBookingSystem.kafka.producer.KafkaProducer;
import com.flight.FlightBookingSystem.flights.service.FlightService;
import com.flight.FlightBookingSystem.passenger.dto.PassengerRepository;
import com.flight.FlightBookingSystem.passenger.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    public Booking createBooking(BookingDto bookingDTO) {
        Flight flight = flightRepository.findById(bookingDTO.getFlight().getId())
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " +bookingDTO.getFlight().getId()));
        Passenger passenger = passengerRepository.findById(bookingDTO.getPassenger().getId())
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + bookingDTO.getPassenger().getId()));

        if(flight.getSeatsAvailable()<=0){
            throw new SeatsNotAvailableException("Insufficient seats Available for the Flight: "+flight.getFlightNumber());
        }
       /* if(isWithinTwoHoursOfDeparture(flight.getDepartureTime())){
            throw new SeatsNotAvailableException("Cannot Process the Booking Request as the Departure Time is Less Than 2Hours");
        }*/

        Booking booking = new Booking();
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setTotalAmount(bookingDTO.getTotalAmount());
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setStatus(BookingStatus.CONFIRMED.toString());
        Booking savedBooking=bookingRepository.save(booking);
        if(savedBooking!=null || savedBooking.getId()!=null){
                int seatsAvailable=booking.getFlight().getSeatsAvailable()-1;

                flightService.updateSeatsAvailable(booking.getFlight().getId(), seatsAvailable);
            kafkaProducer.sendMessage(savedBooking.getStatus());
        }
        System.out.println("----------"+savedBooking.getStatus());
        //kafkaProducer.sendMessage(savedBooking.getStatus());
        return savedBooking;
    }
    public boolean isWithinTwoHoursOfDeparture(LocalDateTime departureTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(currentTime, departureTime);
        return duration.toMinutes() <= 120 && duration.isNegative();
    }

    // Retrieve a booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
    }

    // Cancel a booking by ID
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
        booking.setStatus(BookingStatus.CANCELLED.toString());
        bookingRepository.deleteById(id);
        System.out.println("-------------------"+id);
        if(booking!=null || booking.getId()!=null){
            int seatsAvailable=booking.getFlight().getSeatsAvailable()+1;
            System.out.println("---------flight----------"+id);
             flightService.updateSeatsAvailable(booking.getFlight().getId(), seatsAvailable);
        }


    }

    // List all bookings with pagination and sorting
    public Page<Booking> getAllBookings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookingDate"));
        return bookingRepository.findAll(pageable);
    }
}
