package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.enums.UserRole;
import com.upskill.supportflowlite.model.Executive;
import com.upskill.supportflowlite.model.User;
import com.upskill.supportflowlite.service.ExecutiveService;
import com.upskill.supportflowlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/executive")
public class ExecutiveController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExecutiveService executiveService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addExecutive(@PathVariable int userId,
                                  @RequestBody Executive executive){
       try{
           //Fetch User obj using this userId
           User user = userService.getUserById(userId);
            user.setRole(UserRole.EXECUTIVE);
           //attach user to executive
           executive.setUser(user);
           return ResponseEntity.ok(executiveService.save(executive));
       } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
       }

    }
}
/*
This entire task happens in 2 steps
Step 1:
user post api is called and user is saved in DB and details are returned back to UI-userId
Step 2:
call executive /add api and along with executive info, pass userId as path variable.
Step 3:
Fetch user object based on this ID so that we can validate it
Step 4:
Attach user object to executive and save executive
* */