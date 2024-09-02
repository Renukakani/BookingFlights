package com.flight.FlightBookingSystem;

import com.flight.FlightBookingSystem.flights.controller.FlightController;
import com.flight.FlightBookingSystem.flights.dto.FlightRepository;
import com.flight.FlightBookingSystem.flights.model.Flight;
import com.flight.FlightBookingSystem.flights.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
class FlightBookingSystemApplicationTests {

	@Autowired
	private FlightService flightService;

	@MockBean
	private FlightRepository flightRepository;
	@Test
	public void testGetUserById() {
		Flight flight = new Flight("Spirit4000", "Michigan", "Hartford",
				LocalDateTime.now(), 100, new BigDecimal("200.0"));

		Mockito.when(flightRepository.findById(12L)).thenReturn(Optional.of(flight));

		Flight foundFlight = flightService.getFlightById(12L);
		System.out.println("________----------------"+foundFlight.getFlightNumber());
		assertNotNull(foundFlight);
		assertEquals("Spirit4000", foundFlight.getFlightNumber());
	}
}
