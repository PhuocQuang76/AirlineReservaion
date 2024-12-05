package com.synergisticit.controller;

import com.synergisticit.domain.*;
import com.synergisticit.dto.ReservationDTO;
import com.synergisticit.service.*;
import io.micrometer.common.util.internal.logging.AbstractInternalLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class CheckInController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    PassengerService passengerService;

    @Autowired
    FlightService flightService;

    @Autowired
    AirlineService airlineService;

    @Autowired
    UserService userService;

    @GetMapping("/checkIn")
    public String checkedInForm(Model model,Principal principal) {
        List<Reservation> reservationList = reservationService.findAll();
        model.addAttribute("reservations", reservationList);
        if(principal != null) {
            model.addAttribute("loggedInUserName", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
            model.addAttribute("userId", userId);

        }
        return "checkIn";
    }

    @GetMapping("/reservationDetail")
    public String reservationDetail(@RequestParam Long reservationId, Model model, Principal principal) {

        return "checkIn";

    }
    @GetMapping("/findPassengers")
    public String findPassengers(@RequestParam Long reservationId,@RequestParam Long flightId, Model model) {
        getData(model);
        List<Reservation> reservationList = reservationService.findAll();
        model.addAttribute("reservations", reservationList);

        List<Passenger> passengers = passengerService.findByReservation_ReservationNumbe(reservationId);
        for(Passenger p:passengers){
            System.out.println("p:"+p);
        }

        model.addAttribute("passengerList",passengers);

        Flight flight = flightService.findById(flightId);


        model.addAttribute("flight", flight);

        return "checkIn";
    }

    @PostMapping("/confirmCheckin")
    public String confirmCheckIn(@RequestBody ReservationDTO reservationDTO, Model model){
        Reservation reservation = reservationService.findByReservationNumber(reservationDTO.getReservationNumber());
        reservation.setCheckedBags(reservationDTO.getCheckedBags());
        reservation.setStatus(reservationDTO.getStatus());

        reservationService.update(reservation);
        List<Reservation> reservationList = reservationService.findAll();
        model.addAttribute("reservations", reservationList);
        return "checkIn";
    }

    public void getData(Model model){
        System.out.println("status:" + BookStatus.values());

        model.addAttribute("bookStatus", BookStatus.values());
    }
}