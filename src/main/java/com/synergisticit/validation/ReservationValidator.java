package com.synergisticit.validation;

import com.synergisticit.domain.Reservation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReservationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Reservation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reservation reservation = (Reservation) target;
    }
}
