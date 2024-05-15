package com.rider.ride.validators;

import com.rider.ride.entities.Ride;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component("beforeCreateRideValidator")
public class RideValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Ride.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Ride t = (Ride) target;
        if (checkInputNumber(t.getDriver())) {
            errors.rejectValue("driver", "Driver.empty", "Driver Precisa ser Informado");
        }

        if (checkInputNumber(t.getPassenger())) {
            errors.rejectValue("passenger", "passenger.empty", "Passenger Precisa ser Informado");
        }

        if (checkDecimal(t.getPrice())) {
            errors.rejectValue("price", "price.empty", "Price Precisa ser Informado");
        }

        if (checkInputString(t.getState())) {
            errors.rejectValue("state", "State.empty", "State Precisa ser Informado");
        }

        if (checkInputString(t.getBoardingLocation())) {
            errors.rejectValue("Boarding Location", "BoardingLocation.empty", "BoardingLocation Precisa ser válido");
        }

        if (checkInputString(t.getDestinyLocation())) {
            errors.rejectValue("Destiny Location", "DestinyLocation.empty", "DestinyLocation Precisa ser válido");
        }

    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().isEmpty());
    }

    private boolean checkInputNumber(Integer input) {
        return (input == null);
    }

    private boolean checkDecimal(BigDecimal input) {
        return (input == null);
    }
}