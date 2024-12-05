package com.synergisticit.controller;

import com.google.gson.Gson;
import com.synergisticit.domain.*;
import com.synergisticit.dto.ReservationRequest;
import com.synergisticit.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    @Autowired
    UserService userService;


    @RequestMapping("/reservationForm")
    public String reservationForm(@RequestParam Long flightId, @ModelAttribute Reservation reservation, Model model,Principal principal) {

        model.addAttribute("flightId", flightId);
        List<Reservation> reservationListByFlightID = reservationService.findByFlightId(flightId);
        model.addAttribute("reservations",reservationListByFlightID);

        if(principal != null) {
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            model.addAttribute("userId", userId);
            // Use the flightId as needed

            List<Passenger> passengers = passengerService.findByUserIdAndFlightId(userId,flightId);
            for(Passenger p :passengers){
                System.out.println(p.getPassengerFirstName());
            }

            model.addAttribute("passengers", passengers);


            Flight flight = flightService.findById(flightId);
            System.out.println("flightFormID: " + flight.getFlightId());
            model.addAttribute("bookedFlight",flight);
            // Other logic here

            if(principal != null) {
                model.addAttribute("loggedInUserName", principal.getName());
                User userD = userService.findUserByUserName(principal.getName());
                Long userI = userDB.getUserId();
                List<Role> rolesDB = userDB.getRoles();
                model.addAttribute("roles", rolesDB);
                model.addAttribute("userId", userId);

            }
            return "reservationForm";
        }
        return null;
    }

    @RequestMapping("/success")
    public String success( Model model,Principal principal) {



        if(principal != null) {
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            model.addAttribute("userId", userId);
            // Use the flightId as needed


            if(principal != null) {
                model.addAttribute("loggedInUserName", principal.getName());
                User userD = userService.findUserByUserName(principal.getName());
                Long userI = userDB.getUserId();
                List<Role> rolesDB = userDB.getRoles();
                model.addAttribute("roles", rolesDB);
                model.addAttribute("userId", userId);

            }
            return "success";
        }
        return null;
    }


//
//    @RequestMapping("/saveReservation")
//    public String saveReservation(@RequestBody ReservationRequest reservationRequest, Model model){
//        Reservation reservation = new Reservation();
//        reservation.setFlightId(reservationRequest.getFlightId());
//        reservation.setCheckedBags(reservationRequest.getCheckedBags());
//        reservation.setStatus(reservationRequest.getStatus());
//
//        List<Passenger> passengers = new ArrayList<>();
//        for(Passenger passengerRequest : reservationRequest.getPassengers()){
//            Passenger passenger = new Passenger();
//            // Set passenger details
//            passenger.setFlightId(passengerRequest.getFlightId());
//            passenger.setPassengerDOB(passengerRequest.getPassengerDOB());
//            passenger.setPassengerEmail(passengerRequest.getPassengerEmail());
//            passenger.setPassengerGender(passengerRequest.getPassengerGender());
//            passenger.setPassengerFirstName(passengerRequest.getPassengerFirstName());
//            passenger.setPassengerLastName(passengerRequest.getPassengerLastName());
//            passenger.setPassengerPhoneNo(passengerRequest.getPassengerPhoneNo());
//            passenger.setPassengerAddress(passengerRequest.getPassengerAddress());
//
//
//            // ... other passenger details
//            passengers.add(passenger);
//        }
//
//        reservation.setPassengers(passengers);
//
//        // Establish the association and save the changes
//        for(Passenger passenger : passengers){
//            passenger.getReservations().add(reservation);
//            passengerService.save(passenger);
//        }
//
//        reservationService.save(reservation);
//
//        return "reservationForm";
//    }

    @RequestMapping("/saveReservation")
    public Reservation saveReservation(@RequestBody ReservationRequest reservationRequest, Model model){
        Reservation reservation = new Reservation();
        reservation.setFlightId(reservationRequest.getFlightId());
        reservation.setCheckedBags(reservationRequest.getCheckedBags());
        reservation.setStatus(reservationRequest.getStatus());

        List<Passenger> passengers = new ArrayList<>();
        for(Passenger passengerRequest : reservationRequest.getPassengers()){
            Passenger passenger = new Passenger();
            // Set passenger details
            passenger.setFlightId(passengerRequest.getFlightId());
            passenger.setPassengerDOB(passengerRequest.getPassengerDOB());
            passenger.setPassengerEmail(passengerRequest.getPassengerEmail());
            passenger.setPassengerGender(passengerRequest.getPassengerGender());
            passenger.setPassengerFirstName(passengerRequest.getPassengerFirstName());
            passenger.setPassengerLastName(passengerRequest.getPassengerLastName());
            passenger.setPassengerPhoneNo(passengerRequest.getPassengerPhoneNo());
            passenger.setPassengerAddress(passengerRequest.getPassengerAddress());

            passengers.add(passenger);
        }

        reservation.setPassengers(passengers);

        reservationService.save(reservation);
        for(Passenger passenger : passengers){
            passenger.setReservation(reservation);
            passengerService.save(passenger);
        }
        return reservation;
    }

    @RequestMapping("/deleteReservation")
    public String deleteById(@RequestParam Long reservationNumber, Model model){
        Reservation reservation = reservationService.findByReservationNumber(reservationNumber);
        if(reservation!=null){
            reservationService.deleteById(reservationNumber);
            model.addAttribute("reservations", reservationService.findAll());
            return "redirect:reservationForm";
        }else{
            model.addAttribute("reservations", reservationService.findAll());
        }
        return "reservationForm";
    }

    @RequestMapping("/update/reservation")
    public String update(@RequestBody Reservation reservation , Model model,Principal principal){
//        getData(model);
        reservationService.save(reservation);
        return "reservationForm";
    }

    public List<String> checkedInValue(){
        List<String> checkedInVal = new ArrayList<>();
        checkedInVal.add("Yes");
        checkedInVal.add("No");
        return checkedInVal;
    }

    private void getValues(Model model, Principal principal){
        List<Flight> flights = new ArrayList<>();
        List<Long> flightIds = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();
        List<Long> passengerIds = new ArrayList<>();

        flights = flightService.findAll();
        for(Flight f : flights){
            flightIds.add(f.getFlightId());
        }

        model.addAttribute("flightIds",flightIds);

        passengers = passengerService.findAll();
        for(Passenger p : passengers){
            passengerIds.add(p.getPassengerId());
        }

        model.addAttribute("passengerIds",passengerIds);

        if(principal != null) {
            model.addAttribute("loggedInUser", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
        }
    }

    @PutMapping("/updateReservation/{reservationNumber}/{noOfBag}")
    public Reservation updateReServation(@PathVariable Long reservationNumber,@PathVariable int noOfBag){
        Reservation reservation = reservationService.updateBagAndCheckIn(reservationNumber,noOfBag);
        return reservation;
    }





}
