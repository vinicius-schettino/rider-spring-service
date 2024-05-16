package com.rider.user.validators;

import com.rider.user.entities.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
        if (checkInputString(profile.getName())) {
            errors.rejectValue("name", "name.empty", "Nome Precisa ser Informado");
        }
        if (checkInputEmail(profile.getEmail())) {
            errors.rejectValue("email", "email.empty", "Email Precisa ser válido");
        }
    }

}