package com.rider.user.validators;

import com.rider.user.entities.Address;
import com.rider.user.entities.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component("beforeCreateProfileValidator")
public class ProfileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Profile profile = (Profile) target;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "campo.obrigatorio", "Nome Precisa ser Informado");


        if (checkInputEmail(profile.getEmail())) {
            errors.rejectValue("email", "email.empty", "Email Precisa ser válido");
        }

    }


    private boolean checkInputEmail(String input) {
        return !input.contains("@");
    }

}