package com.msci.carrental.service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.msci.carrental.domain.Car;
import com.msci.carrental.domain.CarDetails;
import com.msci.carrental.dto.BookingRequest;
import com.msci.carrental.dto.CarRentalException;
import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.external.validator.BookingRequestValidator;
import com.msci.carrental.repository.CarDetailsRepository;
import com.msci.carrental.repository.CarRepository;

public class CarRentalServiceImpl implements CarRentalService {

    private BookingService bookingService;

    private CarRepository carRepository;

    private CarDetailsRepository carDetailsRepository;

    private BookingRequestValidator validator;

    public CarRentalServiceImpl(BookingService bookingService, CarRepository carRepository,
            CarDetailsRepository carDetailsRepository, BookingRequestValidator validator) {
        this.bookingService = bookingService;
        this.carRepository = carRepository;
        this.carDetailsRepository = carDetailsRepository;
        this.validator = validator;
    }

    @Override
    public Set<CarType> getAllAvailable(Instant pickUp, Instant dropOff) {
        return carRepository.findAll().stream()
                .filter(car -> bookingService.isAvailable(car, pickUp, dropOff))
                .map(Car::getType)
                .collect(Collectors.toSet());
    }

    @Override
    public CarDetails getDetails(CarType type) {
        return carDetailsRepository.find(type);
    }

    @Override
    public boolean book(BookingRequest request) throws CarRentalException {
        validator.validate(request);
        return bookingService.book(request.getType(), request.getPickUp(), request.getDropOff());
    }

}
