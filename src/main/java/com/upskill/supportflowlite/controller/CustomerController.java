package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.User;
import com.upskill.supportflowlite.service.CustomerService;
import com.upskill.supportflowlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add/v1")
    public Customer postCustomer(@RequestBody Customer customer){
        /* Always save internal linked classes first in the DB(user)
        * External classes should be saved in the end (customer)*/
        User user = customer.getUser(); //{username,password}
        //password is right now in Plain text format
        user = userService.save(user); //{id,username,password}

        //Attach the updated user to customer
        customer.setUser(user);

        //Now Save customer
        return customerService.save(customer);
    }



}
/*
    Option 1:
customer = {  <-- nested structure
    "name" : "",
    "city" : "",
    "user" : {
        "username" : "",
        "password" : ""
    }
}

 UI --> API
* */