package com.synergisticit.controller;

import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;
    @RequestMapping("/roleForm")
    public String roleForm(@ModelAttribute Role role, Model model){
        model.addAttribute("roles", roleService.findAll());
        return "roleForm";
    }

    /*
    @RequestMapping("roleForm")
    public ModelAndView roleForm(Role role) {
        ModelAndView mav = new ModelAndView("roleForm");
        mav.addObject("roles", roleService.findAll());
        return mav;
    }
    */

    @PostMapping("/saveRole")
    public String saveRole( @ModelAttribute Role role, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("hasErrors",true);
            return "roleForm";
        }else{
            roleService.save(role);
            model.addAttribute("roles", roleService.findAll());
            return "redirect:roleForm";
        }
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(@RequestParam Long roleId, Model model){
        Role role = roleService.findById(roleId);
        if(role!=null){
            roleService.deleteById(roleId);
            model.addAttribute("roles", roleService.findAll());
            return "redirect:roleForm";
        }else{
            model.addAttribute("roles", roleService.findAll());
        }
        return "roleForm";
    }


    @RequestMapping("/updateRole")
    public String updateById(@RequestParam Long roleId , Model model){
//        getData(model);
        Role role = roleService.findById(roleId);


        model.addAttribute("role", role);
        model.addAttribute("roles", roleService.findAll());
        return "roleForm";
    }

}
