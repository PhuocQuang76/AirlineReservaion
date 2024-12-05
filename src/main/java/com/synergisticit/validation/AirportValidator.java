package com.synergisticit.validation;

import com.synergisticit.domain.Airport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AirportValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Airport.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Airport airport = (Airport) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportId","","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportCode","","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportName","","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportCity","","");
    }
}
