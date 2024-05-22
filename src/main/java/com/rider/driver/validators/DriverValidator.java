package com.rider.driver.validators;

import com.rider.driver.entities.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
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

        if (driver.getEmail() == null || driver.getEmail().isEmpty()) {
            errors.rejectValue("email", "email.empty", "O email não pode ser vazio.");
        } else if (driver.getEmail().contains(",")) {
            errors.rejectValue("email", "email.invalid", "Insira um email válido");
        }

        // Validação do nome
        if (driver.getName() == null || driver.getName().isEmpty()) {
            errors.rejectValue("name", "name.empty", "O nome não pode ser vazio.");
        } else if (!driver.getName().matches("[a-zA-ZÀ-ÿ\s]+")) {
            errors.rejectValue("name", "name.invalid", "O nome deve conter apenas letras.");
        }
        if (driver.getVehicle() != null) {
            errors.pushNestedPath("vehicle");
            ValidationUtils.invokeValidator(vehicleValidator, driver.getVehicle(), errors);
            errors.popNestedPath();
        }
    }
}