package com.msci.carrental.external;

import java.util.List;
import java.util.Objects;

import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.external.dto.Country;

/**
 * Trivial mock to simulate the external service.
 * Sleeps for a few seconds then returns something.
 */
public class ExternalServiceMock implements ExternalService {

    @Override
    public boolean isValid(CarType type, List<Country> countries) {
        Objects.requireNonNull(type, "ExternalServiceMock : CarType is mandatory");
        Objects.requireNonNull(countries, "ExternalServiceMock : Countries are mandatory");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return type.isForeignType() || countries.stream().anyMatch(Country::isEuMember);
    }
}
