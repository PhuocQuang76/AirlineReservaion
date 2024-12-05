package com.synergisticit.controller;


import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.Search;
import com.synergisticit.domain.User;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class SearchController {
    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @RequestMapping("/searchForm")
    public String searchForm(@ModelAttribute Search search, Model model, Principal principal) {
        String formMessage = null;
        List<Flight> flightList = flightService.findAll();

        if(flightList.size() == 0){
            formMessage = "No flight exist.";
            model.addAttribute("formMessage", formMessage);
        }else{
            model.addAttribute("flights", flightList);
        }

        if(principal != null) {
            model.addAttribute("loggedInUserName", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
            model.addAttribute("userId", userId);

        }
        return "searchForm";
    }




    @PostMapping("/saveSearch")
    public String searchFlights(@ModelAttribute Search search, Model model, Principal principal) {
        String formMessage = null;
        String departtureCity = search.getFlightDepartureCity();
        String arrivalCity = search.getFlightArrivalCity();
        LocalDate from = search.getFlightDepartureDateStart();
        LocalDate to = search.getFlightDepartureDateEnd();

        List<Flight> flights = flightService.searchFlights(departtureCity, arrivalCity, from, to);
        model.addAttribute("SearchFlights", flights);

        List<Flight> flightList = flightService.findAll();

        if(flightList.size() == 0){
            formMessage = "No flight exist.";
            model.addAttribute("formMessage", formMessage);
        }else{
            model.addAttribute("flights", flightList);
        }

        // Pass the username attribute to the view
        if(principal != null) {
            model.addAttribute("loggedInUserName", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
            model.addAttribute("userId", userId);

        }

        return "searchForm";
    }
}
