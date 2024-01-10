package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongTransfersFilterTest {

    FlightFilter filter = new LongTransfersFilter();

    @Test
    void testFlightFilterLongTransfers() {
        LocalDateTime now = LocalDateTime.now();
        Segment shortTransferSegment1 = new Segment(now.minusHours(5), now.minusHours(4));
        Segment shortTransferSegment2 = new Segment(now.minusHours(3), now.minusHours(2));
        Flight shortTransferFlight = new Flight(Arrays.asList(shortTransferSegment1, shortTransferSegment2));

        Segment longTransferSegment1 = new Segment(now.minusDays(1), now.minusDays(1).plusHours(2));
        Segment longTransferSegment2 = new Segment(now, now.plusHours(2));
        Flight longTransferFlight = new Flight(Arrays.asList(longTransferSegment1, longTransferSegment2));

        List<Flight> flights = Arrays.asList(shortTransferFlight, longTransferFlight);

        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(List.of(longTransferFlight), filteredFlights);
    }
}