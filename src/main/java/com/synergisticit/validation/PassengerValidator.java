package com.synergisticit.validation;

import com.synergisticit.domain.Passenger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PassengerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Passenger.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Passenger passenger = (Passenger) target;
    }
}
