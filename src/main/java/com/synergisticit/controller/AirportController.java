package com.synergisticit.controller;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class AirportController {
    @Autowired
    AirportService airportService;

    @Autowired
    UserService userService;

    @RequestMapping("/airportForm")
    public String airportForm(Airport airport, Model model, Principal principal){
        model.addAttribute("airports", airportService.findAll());
        if(principal != null) {
            model.addAttribute("loggedInUser", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
        }
        return "airportForm";
    }

    @RequestMapping("/saveAirport")
    public String saveAirport(@Valid @ModelAttribute Airport airport, BindingResult br, Model model){
        System.out.println("Has Error" + br.hasErrors());
        model.addAttribute("airports",airportService.findAll());

        if(br.hasErrors()){
            model.addAttribute("hasErrors",true);
            System.out.println("2.br.hasError(): " + br.hasErrors());
            return "airportForm";
        }
        else{
            airportService.save(airport);
            model.addAttribute("airports",airportService.findAll());
            return "redirect:airportForm";
        }

    }

    @RequestMapping("/deleteAirport")
    public String deleteById(@RequestParam Long airportId, Model model){
        Airport airport = airportService.findById(airportId);
        if(airport!=null){
            airportService.deleteById(airportId);
            model.addAttribute("airlines", airportService.findAll());
            return "redirect:airportForm";
        }else{
            model.addAttribute("airlines", airportService.findAll());
        }
        return "airportForm";
    }

    @RequestMapping("/updateAirport")
    public String updateById(@RequestParam Long airportId , Model model){
//        getData(model);
        Airport airport = airportService.findById(airportId);


        model.addAttribute("airport", airport);
        model.addAttribute("airports", airportService.findAll());
        return "airportForm";
    }
}
