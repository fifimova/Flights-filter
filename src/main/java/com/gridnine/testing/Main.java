package com.gridnine.testing;

import java.util.List;

public class Main {
    public static FlightFilter filter1 = new DepartureBeforeNowFilter();
    public static FlightFilter filter2 = new ArrivalBeforeDepartureFilter();
    public static FlightFilter filter3 = new LongTransfersFilter();

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Вылет до текущего момента времени: " + filter1.filter(flights));
        System.out.println("Сегменты с датой прилёта раньше даты вылета: " + filter2.filter(flights));
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа: " + filter3.filter(flights));

    }
}