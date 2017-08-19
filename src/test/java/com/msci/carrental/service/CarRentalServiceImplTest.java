package com.msci.carrental.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;
import org.junit.Test;

import com.msci.carrental.ClientDemo;
import com.msci.carrental.dto.BookingRequest;
import com.msci.carrental.dto.CarRentalException;
import com.msci.carrental.dto.CarUsage;
import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.external.dto.Country;

public class CarRentalServiceImplTest {

    @Test
    public void testIfDomesticBookingCanBePerformed() throws CarRentalException {
        // Given
        CarRentalService service = ClientDemo.sut();
        BookingRequest request = request();
        request.setUsage(CarUsage.Domestic);
        // Then
        assertThat(service.book(request)).isTrue();
    }

    @Test
    public void testIfForeignBookingCanBePerformed() throws CarRentalException {
        // Given
        CarRentalService service = ClientDemo.sut();
        BookingRequest request = request();
        request.setUsage(CarUsage.Foreign);
        request.setCountries(Country.randomList());
        // Then
        assertThat(service.book(request)).isTrue();
    }

    private BookingRequest request() {
        BookingRequest request = new BookingRequest();
        request.setType(CarType.OpelAstra);
        Instant now = Instant.now();
        request.setPickUp(now);
        request.setDropOff(now.plus(Duration.ofDays(1)));
        return request;
    }

}
