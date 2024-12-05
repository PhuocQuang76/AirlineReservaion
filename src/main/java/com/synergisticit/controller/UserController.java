package com.synergisticit.controller;


import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserValidator userValidator;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @RequestMapping("/userForm")
    public String userForm(@ModelAttribute User user, Model model, Principal principal){
        int roleDisplay = 0;
        model.addAttribute("roleDisplay", roleDisplay);


        model.addAttribute("users", userService.findAll());
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roleService.findAll());

        if(principal != null) {
            model.addAttribute("loggedInUser", principal.getName());
            User userDB = userService.findUserByUserName(principal.getName());
            List<Role> rolesDB = userDB.getRoles();
            model.addAttribute("roles", rolesDB);
        }
        return "userForm";
    }

    @RequestMapping("saveUser")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("users", userService.findAll());
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("hasErrors", true);
            return "userForm";
        }else{

            model.addAttribute("users", userService.findAll());
            model.addAttribute("roles", roleService.findAll());
            userService.save(user);
            return "redirect:userForm";
        }
    }

    @RequestMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, Model model) {
        int roleDisplay = 1;
        model.addAttribute("roleDisplay",roleDisplay);

        user = userService.findById(user.getUserId());
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("retrievedRole", user.getRoles());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());

        return "userForm";
    }

//    delete?userId=1
    @RequestMapping("/deleteUser")
    public String deleteUser(@ModelAttribute User user, Model model) {
        userService.deleteById(user.getUserId());
        return "redirect:userForm";
    }

    @RequestMapping("/users")
    public String getUsers(){
        userService.findAll();
        return " userForm";
    }
}
