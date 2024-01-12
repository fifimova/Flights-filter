package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LongTransfersFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            long totalGroundTime = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                Segment currentSegment = segments.get(i);
                Segment nextSegment = segments.get(i + 1);

                Duration duration = Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate());
                totalGroundTime += duration.toMinutes();
            }
            if (totalGroundTime >= 120) {
                result.add(flight);
            }
        }
        return result;
    }
}
