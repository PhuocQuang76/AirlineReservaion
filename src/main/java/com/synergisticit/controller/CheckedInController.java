package com.synergisticit.controller;

import com.synergisticit.domain.Reservation;
import com.synergisticit.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class CheckedInController {
    @Autowired
    ReservationService reservationService;
//    @GetMapping("/checkedInForm")
//    public String checkedInForm(){
//
//        return "checkedInForm";
//    }

    @GetMapping("/getAllReservations")
    public List<Reservation> reservationList(){
        return reservationService.findAll();
    }
}
