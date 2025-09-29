package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.model.User;
import com.upskill.supportflowlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @GetMapping("/login")
    public User login(Principal principal){
       String username =  principal.getName();
       return userService.getUserByUsername(username);

    }
}
