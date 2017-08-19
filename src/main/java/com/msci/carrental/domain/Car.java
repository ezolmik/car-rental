package com.msci.carrental.domain;

import com.msci.carrental.external.dto.CarType;

public class Car {

    private int id;

    private CarDetails details;

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public CarDetails getDetails() {
        return details;
    }

    public void setDetails(CarDetails details) {
        this.details = details;
    }

    public CarType getType() {
        return details != null ? details.getType() : null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Car other = (Car) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

}
