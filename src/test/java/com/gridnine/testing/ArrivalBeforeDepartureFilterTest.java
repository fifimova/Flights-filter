package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalBeforeDepartureFilterTest {

    FlightFilter filter = new ArrivalBeforeDepartureFilter();
    @Test
    void testFlightFilterArrivalBeforeDeparture() {
        LocalDateTime now = LocalDateTime.now();
        Segment normalSegment = new Segment(now.minusHours(1), now);
        Segment incorrectSegment = new Segment(now, now.minusHours(1));
        Flight normalFlight = new Flight(List.of(normalSegment));
        Flight incorrectFlight = new Flight(List.of(incorrectSegment));
        List<Flight> flights = Arrays.asList(normalFlight, incorrectFlight);

        List<Flight> filteredFlights = filter.filter(flights);
        assertEquals(List.of(incorrectFlight), filteredFlights);
    }
}