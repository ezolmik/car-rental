package com.msci.carrental.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.repository.CarDetailsRepositoryImpl;
import com.msci.carrental.repository.CarRepositoryImpl;

public class BookingServiceImplTest {

    private BookingService sut = new BookingServiceImpl(new CarRepositoryImpl(new CarDetailsRepositoryImpl()));

    @Test
    public void testCanBookOneVolvo() {
        // When
        boolean actual = sut.book(CarType.VolvoXC60, Instant.now(), Instant.now().plus(Duration.ofDays(1)));
        // Then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void testCanBookTwoVolvos() {
        // When
        sut.book(CarType.VolvoXC60, Instant.now(), Instant.now().plus(Duration.ofDays(1)));
        boolean actual = sut.book(CarType.VolvoXC60, Instant.now(), Instant.now().plus(Duration.ofDays(1)));
        // Then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void testCanNotBookThreeVolvos() {
        // When
        sut.book(CarType.VolvoXC60, Instant.now(), Instant.now().plus(Duration.ofDays(1)));
        sut.book(CarType.VolvoXC60, Instant.now(), Instant.now().plus(Duration.ofDays(1)));
        boolean actual = sut.book(CarType.VolvoXC60, Instant.now(), Instant.now().plus(Duration.ofDays(1)));
        // Then
        assertThat(actual).isEqualTo(false);
    }

}
