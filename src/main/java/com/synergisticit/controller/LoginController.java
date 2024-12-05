package com.synergisticit.controller;


import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {

//    @RequestMapping({"/","/home"})
//    public String home(){
//        return "home";
//    }
//@RequestMapping("/home")
//public String home() {
//    // Redirect to pagedForm
//    return "redirect:/pagedFlights?pageNo=0&pageSize=5&sortedBy=flightId";
//}



    @RequestMapping("/login")
    public String login(@RequestParam(value="logout", required= false) String logout,
                        @RequestParam(value="error", required = false) String error,
                        HttpServletRequest req,
                        HttpServletResponse res,
                        Model model
    ){
        System.out.println("loginToApp()...");


        String message = null;


        if(logout != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null) {
                new SecurityContextLogoutHandler().logout(req, res, auth);
                message="You are logged out.";
            }
        }

        if(error != null) {
            message="Either username or password is incorrect.";
        }

        model.addAttribute("message", message);
        return "loginForm";
    }

    @RequestMapping("accessDenied")
    public String accessDenied() {

        return "accessDenied";
    }





}


