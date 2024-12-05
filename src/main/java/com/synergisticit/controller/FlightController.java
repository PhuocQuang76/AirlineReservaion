package com.synergisticit.controller;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.repository.FlightRepository;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/flightForm")
    public String flightForm(Flight flight, Model model, Principal principal,
                             @RequestParam int pageNo, @RequestParam int pageSize,
                             @RequestParam(required=false) String  sortedBy){

        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortedBy).ascending());
        Page<Flight> pagedFlight = flightRepository.findAll(pageable);
        List<Flight> flights = pagedFlight.getContent();

        model.addAttribute("pagedFlights", flights);
        model.addAttribute("totalPages", pagedFlight.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortedBy", sortedBy);


//        model.addAttribute("pagedFlights", flightService.findAll());
        if(principal != null) {
            model.addAttribute("loggedInUserName", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
            model.addAttribute("userId", userId);

        }
        return "flightForm";
    }

//    @GetMapping("/flightForm")
//    public String flightForm(Flight flight, Model model, Principal principal){
//        model.addAttribute("flights", flightService.findAll());
//        if(principal != null) {
//            model.addAttribute("loggedInUser", principal.getName());
//            User userDB = userService.findUserByUserName(principal.getName());
//            List<Role> rolesDB = userDB.getRoles();
//            model.addAttribute("roles", rolesDB);
//        }
//        return "flightForm";
//    }

    @GetMapping("/flightForm/{field}")
    public String flightFormWithSort(Flight flight, Model model, Principal principal,@PathVariable("field") String field){
        model.addAttribute("flights", flightService.findAllWithSort(field));
        if(principal != null) {
            model.addAttribute("loggedInUser", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
        }
        return "flightForm";
    }



    @PostMapping("/saveFlight")
    public String saveFlight(@Valid @ModelAttribute Flight flight, BindingResult br, Model model){
        System.out.println("Has Error" + br.hasErrors());
        model.addAttribute("flights",flightService.findAll());

        if(br.hasErrors()){
            model.addAttribute("hasErrors",true);
            System.out.println("2.br.hasError(): " + br.hasErrors());
            return "flightForm";
        }
        else{
            flightService.save(flight);
            model.addAttribute("flights",flightService.findAll());
            return "redirect:flightForm";
        }

    }

    @RequestMapping("/deleteFlight")
    public String deleteById(@RequestParam Long flightId, Model model){
        Flight flight = flightService.findById(flightId);
        if(flight!=null){
            flightService.deleteById(flightId);
            model.addAttribute("flights", flightService.findAll());
            return "redirect:flightForm";
        }else{
            model.addAttribute("flights", flightService.findAll());
        }
        return "flightForm";
    }

    @RequestMapping("/updateFlight")
    public String updateById(@RequestParam Long flightId , Model model){
//        getData(model);
        Flight flight = flightService.findById(flightId);


        model.addAttribute("flight", flight);
        model.addAttribute("flights", flightService.findAll());
        return "flightForm";
    }

    @RequestMapping("/flight/{flightNumber}")
    private Flight findByFlightNumber(@PathVariable String flightNumber){
        Flight flight = flightService.findByFlightNumber(flightNumber);
        return flight;
    }

    @GetMapping("/searchFlights")
    private List<Flight> searchFlights(@PathVariable String departtureCity, @PathVariable String arrivalCity, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return flightService.searchFlights(departtureCity,arrivalCity,startDate,endDate);
    }
}
