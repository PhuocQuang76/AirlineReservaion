package com.synergisticit.validation;

import com.synergisticit.domain.Airline;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AirlineValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Airline.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Airline airline = (Airline) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineId", "airline.airlineId.empty", "Airline Id is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineName", "airline.airlineName.empty", "Airline Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineCode", "airline.airlineCode.empty", "Airline Code is required.");
//
    }
}
