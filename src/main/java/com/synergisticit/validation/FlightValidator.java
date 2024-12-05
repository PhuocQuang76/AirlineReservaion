package com.synergisticit.validation;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Flight;
import jakarta.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class FlightValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Flight.class.equals(clazz);
    }
    private Long flightId;

    private String flightNumber;

    @ManyToOne
//	@JoinColumn(name="airlineId")
    private Airline flightAirline;

    private String flightDepartureCity;

    private String flightArrivalCity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate flightDepartureDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime flightDepartureTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate flightArrivalDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime flightArrivalTime;

    private int flightCapacity;

    private double flightPrice;

    private int flightSeatsBooked;
    @Override
    public void validate(Object target, Errors errors) {
        Flight flight = (Flight) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightId","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightNumber","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightAirline","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightDepartureCity","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightArrivalCity","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightArrivalTime","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightCapacity","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportCode","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportName","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportId","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportCode","flight","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportName","flight","");
    }
}
