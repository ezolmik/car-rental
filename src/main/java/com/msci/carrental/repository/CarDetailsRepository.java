package com.msci.carrental.repository;

import com.msci.carrental.domain.CarDetails;
import com.msci.carrental.external.dto.CarType;

public interface CarDetailsRepository {

    CarDetails find(CarType type);
}
