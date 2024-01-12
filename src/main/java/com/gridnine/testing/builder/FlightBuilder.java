package com.gridnine.testing.builder;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Factory class to get sample list of flights.
 */
public class FlightBuilder {
    public static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                createFlight("normal",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                createFlight("normal multi activities",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                createFlight("begin in the past",
                        threeDaysFromNow.minusDays(6), threeDaysFromNow),

                createFlight("end before it begin",
                        threeDaysFromNow, threeDaysFromNow.minusHours(6)),

                createFlight("more than two hours interval",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),

                createFlight("more than two hours interval (2)",
                        threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Flight createFlight(final String description, final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException("you must pass an even number of dates");
        }
        List<Segment> activities = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            activities.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(description, activities);
    }
}
