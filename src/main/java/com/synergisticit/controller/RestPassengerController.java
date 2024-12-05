package com.synergisticit.controller;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
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
public class RestPassengerController{
@Autowired
PassengerService passengerService;


//Create A New Account
@PostMapping(value = "/save/passenger", consumes="application/json;charset=UTF-8", produces= MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> savePassenger(@Valid @RequestBody Passenger passenger, BindingResult br){
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
        Passenger p = passengerService.save(passenger);
        headers.add("New Passenger", p.getPassengerFirstName().toString());
        return new ResponseEntity<Passenger>(p, headers, HttpStatus.CREATED);
    }
}

//Get All Accounts
@GetMapping(value="/passengers", produces= MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<Passenger>> getAllFlight(){
    List<Passenger> passengers = passengerService.findAll();

    for(Passenger passenger:passengers){
        System.out.println("passenger:" + passenger.getPassengerFirstName());
    }
    System.out.println("passenger:" +passengers);

    if(passengers.size() == 0) {
        return new ResponseEntity<List<Passenger>>(passengers, HttpStatus.NOT_FOUND);

    }else {
        for(Passenger passenger:passengers){
            System.out.println("airport return:" + passenger.getPassengerFirstName());
        }

        return new ResponseEntity<List<Passenger>>(passengers, HttpStatus.OK);
    }
}
}
