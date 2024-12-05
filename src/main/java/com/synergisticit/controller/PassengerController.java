package com.synergisticit.controller;

import com.synergisticit.domain.*;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    @Autowired
    UserService userService;

    @RequestMapping("/passengerForm")
    public String passengerForm(@ModelAttribute Passenger passenger, Model model, Principal principal){
        model.addAttribute("passengers", passengerService.findAll());
        if(principal != null) {
            model.addAttribute("loggedInUserName", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userId = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
            model.addAttribute("userId", userId);


            getData(model);


        }

        return "passengerForm";
    }

    @RequestMapping("/savePassenger")
    public String savePassenger(@Valid @ModelAttribute Passenger passenger, BindingResult br, Model model,Principal principal){
        Long userId = passenger.getUserId();
        Long flightId = passenger.getFlightId();
        System.out.println("Has userId" + userId);
        System.out.println("Has flightId" + flightId);
        getData(model);
        System.out.println("Has Error" + br.hasErrors());
        model.addAttribute("passengers",passengerService.findByUserIdAndFlightId(userId,flightId));

        if(principal != null) {
            model.addAttribute("loggedInUserName", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            Long userI = userDB.getUserId();
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
            model.addAttribute("userId", userI);

        }
        if(br.hasErrors()){
            model.addAttribute("hasErrors",true);
            System.out.println("2.br.hasError(): " + br.hasErrors());
            return "passengerForm";
        }
        else{
            passengerService.save(passenger);
            //model.addAttribute("passengers",passengerService.findByUserIdAndFlightId(userId,flightId));
            return "passengerForm";
        }

    }

    @RequestMapping("/deletePassenger")
    public String deleteById(@RequestParam Long passengerId, Model model){
        Passenger passenger = passengerService.findById(passengerId);
        if(passenger!=null){
            passengerService.deleteById(passengerId);
            model.addAttribute("passengers", passengerService.findAll());
            return "redirect:passengerForm";
        }else{
            model.addAttribute("passengers", passengerService.findAll());
        }
        return "passengerForm";
    }

    @RequestMapping("/updatePassenger")
    public String updateById(@RequestParam Long passengerId , Model model){
        getData(model);
        Passenger passenger = passengerService.findById(passengerId);


        model.addAttribute("passenger", passenger);
        model.addAttribute("passengers", passengerService.findAll());
        model.addAttribute("selectedGender", passenger.getPassengerGender());
        return "passengerForm";
    }




    public void getData(Model model){
        System.out.println("Gender:" + Gender.values());

        model.addAttribute("genders", Gender.values());
    }



}
