package com.msci.carrental.domain;

import com.msci.carrental.external.dto.CarType;

public class CarDetails {

    private int price;

    private CarType type;

    public CarDetails(int price, CarType type) {
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public CarType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CarDetails [price=" + price + ", type=" + type + "]";
    }

}
