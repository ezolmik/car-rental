package com.msci.carrental;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import com.msci.carrental.client.Client;
import com.msci.carrental.external.ExternalService;
import com.msci.carrental.external.ExternalServiceMock;
import com.msci.carrental.external.validator.BookingRequestValidator;
import com.msci.carrental.repository.CarDetailsRepository;
import com.msci.carrental.repository.CarDetailsRepositoryImpl;
import com.msci.carrental.repository.CarRepository;
import com.msci.carrental.repository.CarRepositoryImpl;
import com.msci.carrental.service.BookingService;
import com.msci.carrental.service.BookingServiceImpl;
import com.msci.carrental.service.CarRentalService;
import com.msci.carrental.service.CarRentalServiceImpl;

public class ClientDemo {

    private static final String FAILURE_FUTURE = "Future::get was not successful: %s";

    public static void main(String[] args) {
        CarRentalService service = sut();
        ExecutorService es = Executors.newFixedThreadPool(10);
        Queue<Future<String>> results = new LinkedList<>();
        IntStream.range(0, 50).forEach(id ->{
            results.add(es.submit(new Client(id, service)));
        });
        results.stream().forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(String.format(FAILURE_FUTURE, e.getCause()));
            }
        });
        es.shutdown();
    }

    public static CarRentalService sut() {
        CarDetailsRepository carDetailsRepository = new CarDetailsRepositoryImpl();
        CarRepository carRepository = new CarRepositoryImpl(carDetailsRepository);
        BookingService bookingService = new BookingServiceImpl(carRepository);
        ExternalService externalService = new ExternalServiceMock();
        BookingRequestValidator validator = new BookingRequestValidator(externalService);
        return new CarRentalServiceImpl(bookingService, carRepository, carDetailsRepository, validator );
    }
}
