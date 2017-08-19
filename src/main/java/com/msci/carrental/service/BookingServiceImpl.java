package com.msci.carrental.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.msci.carrental.domain.Car;
import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.repository.CarRepository;
import com.msci.carrental.util.Interval;
import com.msci.carrental.util.Intervals;

public class BookingServiceImpl implements BookingService {

    private Map<Car, List<Interval>> bookings = new HashMap<>();

    private CarRepository carRepository;

    public BookingServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        carRepository.findAll().stream().forEach(car -> {
            bookings.put(car, new LinkedList<>());
        });
    }

    @Override
    public boolean isAvailable(Car car, Instant pickUp, Instant dropOff) {
        return Intervals.findPosition(bookings.get(car), pickUp, dropOff) >= 0;
    }

    @Override
    public boolean book(CarType type, Instant pickUp, Instant dropOff) {
        List<Car> cars = carRepository.findAll().stream()
                .filter(car -> car.getType().equals(type))
                .collect(Collectors.toList());
        return bookOne(cars, pickUp, dropOff);
    }

    private synchronized boolean bookOne(List<Car> cars, Instant pickUp, Instant dropOff) {
        boolean booked = false;
        for (int i = 0; !booked && i < cars.size(); i++) {
            List<Interval> intervals = bookings.get(cars.get(i));
            int pos = Intervals.findPosition(intervals, pickUp, dropOff);
            if (pos >= 0) {
                intervals.add(pos, new Interval(pickUp, dropOff));
                booked = true;
            }
        }
        return booked;
    }

}
