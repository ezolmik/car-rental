package com.msci.carrental.dto;

import java.time.Instant;
import java.util.List;

import com.msci.carrental.external.dto.CarType;
import com.msci.carrental.external.dto.Country;

public class BookingRequest {

    private CarType type;

    private CarUsage usage;

    private List<Country> countries;

    private Instant pickUpDate;

    private Instant dropOff;

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Instant getPickUp() {
        return pickUpDate;
    }

    public void setPickUp(Instant pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Instant getDropOff() {
        return dropOff;
    }

    public void setDropOff(Instant dropOff) {
        this.dropOff = dropOff;
    }

    public CarUsage getUsage() {
        return usage;
    }

    public void setUsage(CarUsage usage) {
        this.usage = usage;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}
