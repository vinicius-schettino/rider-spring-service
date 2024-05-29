package com.rider.driver;

import com.rider.driver.entities.Driver;
import com.rider.driver.validators.DriverValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DriverValidatorTest {

    private DriverValidator validator;
    private Driver driver;
    private Errors errors;

    @BeforeEach
    public void setup() {
        validator = new DriverValidator();
        driver = new Driver();
        errors = new BeanPropertyBindingResult(driver, "driver");
    }

    @Test
    public void testEmailEmpty() {
        driver.setEmail("");
        validator.validate(driver, errors);
        assertTrue(errors.hasFieldErrors("email"));
    }

    @Test
    public void testEmailWithComma() {
        driver.setEmail("test,email@example.com");
        validator.validate(driver, errors);
        assertTrue(errors.hasFieldErrors("email"));
    }

    @Test
    public void testValidEmail() {
        driver.setEmail("valid.email@example.com");
        validator.validate(driver, errors);
        assertTrue(!errors.hasFieldErrors("email"));
    }
}
