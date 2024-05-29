package com.rider.driver.validators;

import com.rider.driver.entities.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("beforeCreateDriverValidator")
public class DriverValidator implements Validator {

    @Autowired
    private VehicleValidator vehicleValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Driver.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Driver driver = (Driver) target;

        if (checkEmpty(driver.getName())) {
            errors.rejectValue("name", "name.empty", "Nome precisa ser informado!");
        }

        if (checkEmpty(driver.getEmail())) {
            errors.rejectValue("email", "email.empty", "Email precisa ser informado!");
        }

        if (checkEmailValid(driver.getEmail())) {
            errors.rejectValue("email", "email.invalid", "Email inválido!");
        }

        if (driver.getVehicle() != null) {
            errors.pushNestedPath("vehicle");
            ValidationUtils.invokeValidator(vehicleValidator, driver.getVehicle(), errors);
            errors.popNestedPath();
        }
    }

    private boolean checkEmpty(String input) {
        return (input == null || input.trim().isEmpty());
    }

    private boolean checkEmailValid(String input) {
        return (input.contains(",") || !input.contains("@"));
    }
}
