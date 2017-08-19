package com.msci.carrental.repository;

import java.util.List;

import com.msci.carrental.domain.Car;

public interface CarRepository {

    List<Car> findAll();
}
