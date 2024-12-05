package com.synergisticit.controller;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;
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
public class RestAirportController {
    @Autowired
    AirportService airportService;


    //Create A New Account
    @PostMapping(value = "/save/airport", consumes="application/json;charset=UTF-8", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAirport(@Valid @RequestBody Airport airport, BindingResult br){
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
            Airport ap = airportService.save(airport);
            headers.add("New Airline", ap.getAirportCity().toString());
            return new ResponseEntity<Airport>(ap, headers, HttpStatus.CREATED);
        }
    }

    //Get All Accounts
    @GetMapping(value="/airports", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airport>> getAllAirport(){
        List<Airport> airports = airportService.findAll();

        for(Airport ap:airports){
            System.out.println("ap:" + ap.getAirportName());
        }
        System.out.println("airports:" +airports);

        if(airports.size() == 0) {
            return new ResponseEntity<List<Airport>>(airports, HttpStatus.NOT_FOUND);

        }else {
            for(Airport airport:airports){
                System.out.println("airport return:" + airport.getAirportName());
            }

            return new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);
        }
    }



}
