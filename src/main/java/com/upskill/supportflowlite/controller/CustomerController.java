package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.dto.CustomerProfileDto;
import com.upskill.supportflowlite.enums.UserRole;
import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.Product;
import com.upskill.supportflowlite.model.Ticket;
import com.upskill.supportflowlite.model.User;
import com.upskill.supportflowlite.service.CustomerService;
import com.upskill.supportflowlite.service.ProductService;
import com.upskill.supportflowlite.service.TicketService;
import com.upskill.supportflowlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add/v1")
    public Customer postCustomer(@RequestBody Customer customer){
        /* Always save internal linked classes first in the DB(user)
        * External classes should be saved in the end (customer)*/
        User user = customer.getUser(); //{username,password}
        //attach the role
        user.setRole(UserRole.CUSTOMER);

        //password is right now in Plain text format so convert it into bcrypt encryption
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.save(user); //{id,username,password}

        //Attach the updated user to customer
        customer.setUser(user);

        //Now Save customer
        return customerService.save(customer);
    }

    /*
    * AIM: Fetch single customer with its tickets and products info
    * */
    @GetMapping("/one/{customerId}")
    public  CustomerProfileDto getCustomerProfile(@PathVariable int customerId,
                                                  CustomerProfileDto customerProfileDto){
        /* Step 1: Fetch Customer Object using customerId -- Validation */
        Customer customer = customerService.fetchCustomerById(customerId);
        /* Step 2: Fetch Products using customerId -- List<Product> */
        List<Product> listProducts = productService.getProductsByCustomerId(customerId);
        /* Step 3: Fetch Tickets using customerId -- List<Ticket> */
        List<Ticket> listTickets = ticketService.getTicketsByCustomerId(customerId);
        /* Step 4: Give response using DTO / HashMap */
       /*
        Map<String,Object> map = new HashMap<>();
        map.put("customer", customer);
        map.put("product_list", listProducts);
        map.put("tickets_list", listTickets);
        return map;
        */
        customerProfileDto.setId(customerId);
        customerProfileDto.setName(customer.getName());
        /* Convert Ticket into Ticket Dto */
        List<CustomerProfileDto.TicketDto> listTicketDto = new ArrayList<>();

        for(Ticket ticket : listTickets){
            CustomerProfileDto.TicketDto tDto = new CustomerProfileDto.TicketDto();
            tDto.setId(ticket.getId());
            tDto.setStatus(ticket.getStatus());
            tDto.setPriority(ticket.getPriority());
            tDto.setSubject(ticket.getSubject());
            listTicketDto.add(tDto);
        }
        customerProfileDto.setListTicketsDto(listTicketDto);

        // Convert List<Product> into List<ProductDto>
        List<CustomerProfileDto.ProductDto> listProductDto = new ArrayList<>();

        for(Product product: listProducts){
            //convert product to productDto
            CustomerProfileDto.ProductDto pDto = new CustomerProfileDto.ProductDto();
            pDto.setId(product.getId());
            pDto.setPlanName(product.getPlanName());
            listProductDto.add(pDto);
        }

        customerProfileDto.setListProductDto(listProductDto);
        return customerProfileDto;
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