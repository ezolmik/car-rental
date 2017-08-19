package com.msci.carrental.service;

import java.time.Instant;

import com.msci.carrental.domain.Car;
import com.msci.carrental.external.dto.CarType;

public interface BookingService {

    boolean isAvailable(Car car, Instant pickUp, Instant dropOff);

    boolean book(CarType type, Instant pickUp, Instant returnDate);
}
