package com.synergisticit.controller;

import com.synergisticit.domain.Airline;
import com.synergisticit.service.AirlineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestAirlineController {

    @Autowired
    AirlineService airlineService;


    //Create A New Account
    @PostMapping(value = "/save/airline", consumes="application/json;charset=UTF-8", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAirport(@Valid @RequestBody Airline airline, BindingResult br){
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
            Airline al = airlineService.save(airline);
            //headers.add("New Airline", al.getAirlineCode().toString());
            return new ResponseEntity<Airline>(al, headers, HttpStatus.CREATED);
        }
    }

    //Get All Accounts
    @GetMapping(value="/airlines", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airline>> getAllAirLine(){
        List<Airline> airlines = airlineService.findAll();

        for(Airline al:airlines){
            System.out.println("al:" + al.getAirlineName());
        }
        System.out.println("airLines:" +airlines);

        if(airlines.size() == 0) {
            return new ResponseEntity<List<Airline>>(airlines, HttpStatus.NOT_FOUND);

        }else {
            for(Airline airline:airlines){
                System.out.println("airline return:" + airline.getAirlineName());
            }

            return new ResponseEntity<List<Airline>>(airlines, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteAirline/{airlineId}")
    public ResponseEntity<Airline> deleteAirline(@PathVariable Long airlineId){
        HttpHeaders httpHeaders = new HttpHeaders();

        Airline airline = airlineService.findById(airlineId);
        if(airline==null){
            System.out.println("Airline is not existed.");
            return new ResponseEntity<Airline>(airline, HttpStatus.NOT_FOUND);
        }else{
            System.out.println("Deleting an account -------");
            airlineService.deleteById(airlineId);
            System.out.println("Delete airline: " + airline);

            return new ResponseEntity<Airline>(airline, HttpStatus.OK);

        }
    }

    @PutMapping("/updateAirline")
    public ResponseEntity<Airline> updateAccount(@RequestBody Airline airline){
        HttpHeaders httpHeaders = new HttpHeaders();

        Airline updateAirline = airlineService.findById(airline.getAirlineId());
        if(updateAirline==null){
            System.out.println("Airline is not existed.");
            return new ResponseEntity<Airline>(airline, HttpStatus.NOT_FOUND);
        }else{
            System.out.println("Updating an airline -------");
            Airline al = airlineService.update(updateAirline);
            System.out.println("Update account: " + al);

            return new ResponseEntity<Airline>(al, HttpStatus.OK);

        }
    }

    @PutMapping("/update/airline")
    public ResponseEntity<Airline> updateAirline(@RequestBody Airline airline){
        HttpHeaders httpHeaders = new HttpHeaders();

        Airline updateAccount = airlineService.findById(airline.getAirlineId());
        if(airline==null){
            System.out.println("Airline is not existed.");
            return new ResponseEntity<Airline>(airline, HttpStatus.NOT_FOUND);
        }else{
            System.out.println("Updating an account -------");
            Airline ac = airlineService.update(updateAccount);
            System.out.println("Update account: " + ac);

            return new ResponseEntity<Airline>(ac, HttpStatus.OK);

        }
    }

}
