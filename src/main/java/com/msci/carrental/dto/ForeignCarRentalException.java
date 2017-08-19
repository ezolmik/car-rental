package com.msci.carrental.dto;

public class ForeignCarRentalException extends CarRentalException {

    private static final long serialVersionUID = 1L;

    public ForeignCarRentalException(String message) {
        super(message);
    }

}
