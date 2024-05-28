package com.rider.driver.validators;

import com.rider.driver.entities.Vehicle;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateVehicleValidator")
public class VehicleValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Vehicle.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Vehicle vehicle = (Vehicle) target;

        if (checkEmpty(vehicle.getPlate())) {
            errors.rejectValue("plate", "name.empty", "Placa precisa ser informada!");
        }

        if (checkPlateValid(vehicle.getPlate())) {
            errors.rejectValue("plate", "name.invalid", "Placa precisa estar no padrão!");
        }

    }
    private boolean checkEmpty(String input) {
        return (input == null || input.trim().isEmpty());
    }

    private boolean checkPlateValid(String input) {
        return (input.matches("[A-Z]{3}[0-9]{4}"));
    }

}