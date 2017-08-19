package com.msci.carrental.external;

import java.util.List;

import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.external.dto.Country;

public interface ExternalService {

    boolean isValid(CarType type, List<Country> countries);

}
