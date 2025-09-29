package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.model.User;
import com.upskill.supportflowlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/add")
    public User postUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }
}
