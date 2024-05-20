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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cep", "campo.obrigatorio", "O campo cep é obrigatório");


        if (!isValidCep(address.getCEP())) {
            errors.rejectValue("cep", "cep.invalido", "CEP inválido");
        }
    }

    private boolean isValidCep(String cep) {
        return cep != null && cep.matches("\\d{5}-\\d{3}");
    }
}