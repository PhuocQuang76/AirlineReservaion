package com.synergisticit.controller;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestRoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRole(){
        List<Role> roles = roleService.findAll();
        if(roles.size() == 0) {
            return new ResponseEntity<List<Role>>(roles, HttpStatus.NOT_FOUND);

        }else {
            for(Role r:roles){
                System.out.println("u return:" + r.getRoleId());
            }

            return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
        }
    }

    @PostMapping("/save/role")
    public ResponseEntity<?> saveRole(@Valid @RequestBody Role role, BindingResult br){
        HttpHeaders httpHeader = new HttpHeaders();
        StringBuilder sb = new StringBuilder("");
        if(br.hasErrors()){
            List<FieldError> fieldErrors = br.getFieldErrors();
            System.out.println("No. Of Errors: "+fieldErrors.size());
            for(FieldError fieldError : fieldErrors) {
                sb = sb.append("\""+ fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
            }
            httpHeader.add("errors",sb.toString());
            return new ResponseEntity<StringBuilder>(sb, httpHeader, HttpStatus.NOT_FOUND);
        }else{
            Role r = roleService.save(role);
            httpHeader.add("New Role", r.getRoleName().toString());
            return new ResponseEntity<Role>(r, httpHeader, HttpStatus.CREATED);

        }
    }

    @DeleteMapping("/delete/role/{roleId}")
    public ResponseEntity<Role> deleteRole(@PathVariable Long roleId){
        HttpHeaders httpHeaders = new HttpHeaders();

        Role role = roleService.findById(roleId);
        if(role==null){
            System.out.println("role is not existed.");
            return new ResponseEntity<Role>(role, HttpStatus.NOT_FOUND);
        }else{
            System.out.println("Deleting an customer -------");
            roleService.deleteById(roleId);
            System.out.println("Delete Customer: " + role);
            return new ResponseEntity<Role>(role, HttpStatus.OK);
        }
    }


    @PutMapping(value = "/update/role", consumes="application/json", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateCustomer(@RequestBody Role role){
        HttpHeaders httpHeaders = new HttpHeaders();

        Role updateRole = roleService.findById(role.getRoleId());
        if(updateRole==null){
            System.out.println("Role is not existed.");
            return new ResponseEntity<Role>(updateRole, HttpStatus.NOT_FOUND);
        }else{
            System.out.println("Updating an role -------");
            Role r = roleService.update(role);
            System.out.println("Update role: " + r.getRoleId());

            return new ResponseEntity<Role>(r, HttpStatus.OK);

        }
    }
}
