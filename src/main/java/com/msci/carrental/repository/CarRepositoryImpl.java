package com.msci.carrental.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.msci.carrental.domain.Car;
import com.msci.carrental.external.dto.CarType;

public class CarRepositoryImpl implements CarRepository {

    private static final int SIZE = 5;

    private List<Car> cars;

    public CarRepositoryImpl(CarDetailsRepository carDetailsRepository) {
        cars = new ArrayList<>();
        IntStream.range(1, SIZE).forEach(id -> {
            Car car = new Car(id);
            CarType type = id % 2 == 1 ? CarType.OpelAstra : CarType.VolvoXC60;
            car.setDetails(carDetailsRepository.find(type));
            cars.add(car);
        });
    }

    @Override
    public List<Car> findAll() {
        return Collections.unmodifiableList(cars);
    }
}
