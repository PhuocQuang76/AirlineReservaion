package com.synergisticit.controller;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.AirlineService;
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
public class AirlineController {
    @Autowired
    AirlineService airlineService;

    @Autowired
    UserService userService;

    @RequestMapping("/airlineForm")
    public String airlineForm(Airline airline, Model model, Principal principal){
        model.addAttribute("airlines", airlineService.findAll());

        if(principal != null) {
            model.addAttribute("loggedInUser", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
        }
        return "airlineForm";
    }

    @RequestMapping("/saveAirline")
    public String saveAirline(@Valid @ModelAttribute Airline airline, BindingResult br, Model model){
        System.out.println("Has Error" + br.hasErrors());
        model.addAttribute("airlines",airlineService.findAll());

        if(br.hasErrors()){
            model.addAttribute("hasErrors",true);
            System.out.println("2.br.hasError(): " + br.hasErrors());
            return "airlineForm";
        }
        else{
            airlineService.save(airline);
            model.addAttribute("airlines",airlineService.findAll());
            return "redirect:airlineForm";
        }

    }

    @RequestMapping("/deleteAirline")
    public String deleteById(@RequestParam Long airlineId, Model model){
        Airline airline = airlineService.findById(airlineId);
        if(airline!=null){
            airlineService.deleteById(airlineId);
            model.addAttribute("airlines", airlineService.findAll());
            return "redirect:airlineForm";
        }else{
            model.addAttribute("airlines", airlineService.findAll());
        }
        return "airlineForm";
    }

    @RequestMapping("/updateAirline")
    public String updateById(@RequestParam Long airlineId , Model model){
//        getData(model);
        Airline airline = airlineService.findById(airlineId);


        model.addAttribute("airline", airline);
        model.addAttribute("airlines", airlineService.findAll());
        return "airlineForm";
    }

}
