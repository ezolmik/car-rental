package com.msci.carrental.service;

import java.time.Instant;
import java.util.Set;

import com.msci.carrental.domain.CarDetails;
import com.msci.carrental.dto.BookingRequest;
import com.msci.carrental.dto.CarRentalException;
import com.msci.carrental.external.dto.CarType;

public interface CarRentalService {

    Set<CarType> getAllAvailable(Instant pickUp, Instant dropOff);

    CarDetails getDetails(CarType type);

    boolean book(BookingRequest request) throws CarRentalException;

}
