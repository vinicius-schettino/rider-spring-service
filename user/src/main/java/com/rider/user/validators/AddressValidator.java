package com.rider.user.validators;

import com.rider.user.entities.Address;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("beforeCreateAddressValidator")
public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Address address = (Address) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "neighborhood","campo.obrigatorio", "O campo bairro é obrigatório");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "campo.obrigatorio", "O campo rua é obrigatório");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "campo.obrigatorio", "O campo número é obrigatório");

        if (address.getCEP() == null) {
            errors.rejectValue("cep", "campo.obrigatorio", "O CEP é obrigatório");
        } else if (!isValidCep(address.getCEP())) {
            errors.rejectValue("cep", "cep.invalido", "CEP inválido");
        }
    }

    private boolean isValidCep(Integer cep) {
        int minCep = 1000000;
        int maxCep = 99999999;
        return cep >= minCep && cep <=maxCep;
    }
}