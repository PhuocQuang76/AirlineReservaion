package com.synergisticit.controller;

import com.synergisticit.domain.Flight;
import com.synergisticit.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    FlightRepository flightRepository;
    //localhost:9090/pagedFlights?pageNo=1&pageSize=5&sortedBy=flightId
//    @RequestMapping("/home")
//    public String findFlights2(@RequestParam int pageNo, @RequestParam int pageSize,
//                               @RequestParam(required=false) String  sortedBy, Model model){
//        System.out.println("sortedBy: "+sortedBy);
//        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortedBy).ascending());
//        //pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
//        Page<Flight> pagedFlight = flightRepository.findAll(pageable);
//        List<Flight> flights = pagedFlight.getContent();
//
//        model.addAttribute("flights", flights);
//        model.addAttribute("totalPages", pagedFlight.getTotalPages());
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("sortedBy", sortedBy);
//        return "pagedFlight";
//    }
    @RequestMapping("/home")
    public String home() {
        // Redirect to pagedForm
        return "redirect:/pagedFlights?pageNo=0&pageSize=5&sortedBy=flightId";
    }
}
