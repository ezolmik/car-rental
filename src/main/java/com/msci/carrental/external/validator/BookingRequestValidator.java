package com.msci.carrental.external.validator;

import com.msci.carrental.dto.BookingRequest;
import com.msci.carrental.dto.CarRentalException;
import com.msci.carrental.dto.CarUsage;
import com.msci.carrental.dto.ForeignCarRentalException;
import com.msci.carrental.external.ExternalService;

public class BookingRequestValidator implements Validator<BookingRequest>{

    private ExternalService externalService;

    public BookingRequestValidator(ExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public void validate(BookingRequest request) throws CarRentalException {
        if (request == null || request.getDropOff() == null || request.getPickUp() == null
                || request.getType() == null || request.getUsage() == null) {
            throw new CarRentalException("All fields except countries are mandatory!");
        } else if (CarUsage.Foreign.equals(request.getUsage()) && request.getCountries() == null) {
            throw new ForeignCarRentalException("Specify countries or use domestic rental type!");
        } else if (CarUsage.Foreign.equals(request.getUsage()) && !externalService.isValid(request.getType(), request.getCountries())) {
            throw new ForeignCarRentalException(
                    "Car type = " + request.getType()
                    + " can not be used in the specified countries = " + request.getCountries());
        }
    }

}
