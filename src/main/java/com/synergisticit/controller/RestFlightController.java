package com.synergisticit.controller;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestFlightController {
    @Autowired
    FlightService flightService;


    //Create A New Account
    @PostMapping(value = "/save/flight", consumes="application/json;charset=UTF-8", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFlight(@Valid @RequestBody Flight flight, BindingResult br){
        HttpHeaders headers = new HttpHeaders();
        StringBuilder sb = new StringBuilder("");
        if(br.hasFieldErrors()){
            List<FieldError> fieldErrors = br.getFieldErrors();
            System.out.println("No. Of Errors: "+fieldErrors.size());
            for(FieldError fieldError : fieldErrors) {
                sb = sb.append("\""+ fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
            }
            headers.add("errors", sb.toString());
            return new ResponseEntity<StringBuilder>(sb, headers, HttpStatus.NOT_ACCEPTABLE);
        }else {
            Flight f = flightService.save(flight);
            headers.add("New Airline", f.getFlightNumber().toString());
            return new ResponseEntity<Flight>(f, headers, HttpStatus.CREATED);
        }
    }

    //Get All Accounts
    @GetMapping(value="/flights")
    public ResponseEntity<List<Flight>> getAllFlight(){
        List<Flight> flights = flightService.findAll();

        for(Flight flight:flights){
            System.out.println("flight:" + flight.getFlightNumber());
        }
        System.out.println("flights:" +flights);

        if(flights.size() == 0) {
            return new ResponseEntity<List<Flight>>(flights, HttpStatus.NOT_FOUND);

        }else {
            for(Flight flight:flights){
                System.out.println("airport return:" + flight.getFlightNumber());
            }

            return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
        }
    }
}
