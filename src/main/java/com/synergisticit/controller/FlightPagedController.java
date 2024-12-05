package com.synergisticit.controller;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.repository.FlightRepository;
import com.synergisticit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.List;

@Controller
public class FlightPagedController {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    UserService userService;


    //localhost:9090/findTheFlights?pageNo=1&pageSize=5&sortedBy=flightId
    @RequestMapping("findTheFlights")
    public ResponseEntity<List<Flight>> findFlights(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortedBy,Model model) {
        Pageable pageable = null;
        //pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
        pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy).descending());

        Page<Flight> pagedFlight = flightRepository.findAll(pageable);
        List<Flight> flights = pagedFlight.getContent();
        return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
    }

    //localhost:9090/pagedFlights?pageNo=1&pageSize=5&sortedBy=flightId
    @RequestMapping("pagedFlights")
    public String findFlights2(@RequestParam int pageNo, @RequestParam int pageSize,
                               @RequestParam(required=false) String  sortedBy, Model model, Principal principal){
        System.out.println("sortedBy: "+sortedBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortedBy).ascending());
        //pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
        Page<Flight> pagedFlight = flightRepository.findAll(pageable);
        List<Flight> flights = pagedFlight.getContent();

        model.addAttribute("flights", flights);
        model.addAttribute("totalPages", pagedFlight.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortedBy", sortedBy);

        if(principal != null) {
            model.addAttribute("loggedInUserName", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
            model.addAttribute("userId", userId);

        }
        return "pagedFlight";
    }


}
