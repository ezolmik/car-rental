package com.msci.carrental.repository;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import com.msci.carrental.domain.CarDetails;
import com.msci.carrental.external.dto.CarType;

public class CarDetailsRepositoryImpl implements CarDetailsRepository {

    private static final int CHEAP = 10;
    private static final int EXPENSIVE = 100;

    private Map<CarType, CarDetails> details = new EnumMap<>(CarType.class);

    public CarDetailsRepositoryImpl() {
        Arrays.stream(CarType.values()).forEach(type -> {
            details.put(type, details(type));
        });
    }

    @Override
    public CarDetails find(CarType type) {
        return details.get(type);
    }

    private CarDetails details(CarType type) {
        int price = type.isForeignType() ? CHEAP : EXPENSIVE;
        return new CarDetails(price, type);
    }

}
