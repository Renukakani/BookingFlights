package com.flight.FlightBookingSystem.flights.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlightDto {
    private String flightNumber;
    private LocalDateTime departureTime;
    private String departureCity;
    private String arrivalCity;
    private Integer seatsAvailable;
    private BigDecimal pricePerSeat;

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public BigDecimal getPricePerSeat() {
        return pricePerSeat;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setPricePerSeat(BigDecimal pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }
}
