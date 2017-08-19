package com.msci.carrental.client;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.msci.carrental.dto.BookingRequest;
import com.msci.carrental.dto.CarRentalException;
import com.msci.carrental.dto.CarUsage;
import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.external.dto.Country;
import com.msci.carrental.service.CarRentalService;

public class Client implements Callable<String> {

    private static final String SUCCESS_BOOK = "Successful booking request. Client id = %s";
    private static final String SUCCESS_GETALL = "Successful getAllAvailable call. Result = %s";
    private static final String SUCCESS_DETAILS = "Successful getDetails call. Result = %s";
    private static final String FAILURE_BOOK = "Unsuccessful booking request. Client id = %s, message = %s";

    private int id;
    private CarRentalService service;

    public Client(int id, CarRentalService service) {
        this.id = id;
        this.service = service;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        if (id % 3 == 0) {
            try {
                service.book(request(id));
                sb.append(String.format(SUCCESS_BOOK, id));
            } catch (CarRentalException e) {
                sb.append(String.format(FAILURE_BOOK, id, e.getMessage()));
            }
        } else {
            Instant now = Instant.now();
            int rand = ThreadLocalRandom.current().nextInt(31);
            Set<CarType> cars = service.getAllAvailable(now.plus(Duration.ofDays(rand)),
                    now.plus(Duration.ofDays(rand + 1)));
            if (id % 3 == 1) {
                sb.append(String.format(SUCCESS_GETALL, cars.stream().map(CarType::toString).collect(Collectors.joining(", "))));
            } else {
                List<CarType> carList = cars.stream().collect(Collectors.toList());
                Collections.shuffle(carList);
                sb.append(String.format(SUCCESS_DETAILS, service.getDetails(carList.get(0))));
            }
        }
        return sb.toString();
    }

    private BookingRequest request(int id) {
        BookingRequest request = new BookingRequest();
        if (id % 2 == 0) {
            request.setUsage(CarUsage.Foreign);
            request.setCountries(Country.randomList());
        } else {
            request.setUsage(CarUsage.Domestic);
        }
        request.setType(CarType.random());
        Instant now = Instant.now();
        int rand = ThreadLocalRandom.current().nextInt(31);
        request.setPickUp(now.plus(Duration.ofDays(rand)));
        request.setDropOff(now.plus(Duration.ofDays(rand + 1)));
        return request;
    }

}