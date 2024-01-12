package com.gridnine.testing;

import com.gridnine.testing.filter.DepartureBeforeNowFilter;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartureBeforeNowFilterTest {

    FlightFilter filter = new DepartureBeforeNowFilter();

    @Test
    void testFlightFilterDepartureBeforeNow() {
        LocalDateTime now = LocalDateTime.now();
        Segment pastSegment = new Segment(now.minusDays(2), now.minusDays(1));
        Segment futureSegment = new Segment(now.plusDays(1), now.plusDays(2));
        Flight pastFlight = new Flight("description", List.of(pastSegment));
        Flight futureFlight = new Flight("description", List.of(futureSegment));
        List<Flight> flights = Arrays.asList(pastFlight, futureFlight);

        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(List.of(pastFlight), filteredFlights);
    }
}