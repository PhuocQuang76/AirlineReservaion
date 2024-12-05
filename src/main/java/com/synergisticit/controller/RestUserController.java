package com.synergisticit.controller;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;
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
public class RestUserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.findAll();
        if(users.size() == 0) {
            return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);

        }else {
            for(User u:users){
                System.out.println("u return:" + u.getUserId());
            }

            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
    }



    @PostMapping(value = "save/user", consumes="application/json;charset=UTF-8", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult br){
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
            User u = userService.save(user);
            httpHeader.add("New User", u.getUserId().toString());
            return new ResponseEntity<User>(u, httpHeader, HttpStatus.CREATED);

        }
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<User> deleteBranch(@PathVariable Long userId){
        HttpHeaders httpHeaders = new HttpHeaders();

        User user = userService.findById(userId);
        if(user==null){
            System.out.println("user is not existed.");
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }else{
            System.out.println("Deleting an customer -------");
            userService.deleteById(userId);
            System.out.println("Delete Customer: " + user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }


    @PutMapping(value = "/update/user", consumes="application/json", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateCustomer(@RequestBody User user){
        HttpHeaders httpHeaders = new HttpHeaders();

        User updateUser = userService.findById(user.getUserId());
        if(updateUser==null){
            System.out.println("User is not existed.");
            return new ResponseEntity<User>(updateUser, HttpStatus.NOT_FOUND);
        }else{
            System.out.println("Updating an user -------");
            User u = userService.update(user);
            System.out.println("Update user: " + u.getUserId());

            return new ResponseEntity<User>(u, HttpStatus.OK);

        }
    }


}
