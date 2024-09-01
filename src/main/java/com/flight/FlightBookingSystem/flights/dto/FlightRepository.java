package com.flight.FlightBookingSystem.flights.dto;

import com.flight.FlightBookingSystem.flights.model.Flight;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Flight  SET seats_available = :seats WHERE id = :id",nativeQuery = true)
    void updateSeatsAvailable(@Param("id") Long id, @Param("seats") int seats);
}
