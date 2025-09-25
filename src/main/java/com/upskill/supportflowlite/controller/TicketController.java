package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.Ticket;
import com.upskill.supportflowlite.service.CustomerService;
import com.upskill.supportflowlite.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add/v1/{customerId}")
    public Ticket postTicketV1(@PathVariable int customerId ,
                               @RequestBody Ticket ticket){
        //Step 1: Fetch customer obj by customerId -- validation
        Customer customer =  customerService.fetchCustomerById(customerId);
        //Step 2: attach customer obj to ticket
        ticket.setCustomer(customer);
        //Step 3: Save ticket
        return ticketService.save(ticket);

    }

    @PostMapping("/add/v2")
    public ResponseEntity<?> postTicketV2(@RequestParam("customerUsername") String customerUsername,
                                          @RequestBody Ticket ticket){
        try {
            //Step 1: Fetch Customer Obj using username
            Customer customer = customerService.fetchCustomerByUsername(customerUsername);
            //Step 2: Attach customer to ticket
            ticket.setCustomer(customer);
            //Step 3: Save Ticket
            return ResponseEntity.ok(ticketService.save(ticket));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
