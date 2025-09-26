package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.dto.TicketDto;
import com.upskill.supportflowlite.enums.Priority;
import com.upskill.supportflowlite.enums.Status;
import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.Executive;
import com.upskill.supportflowlite.model.Ticket;
import com.upskill.supportflowlite.service.CustomerService;
import com.upskill.supportflowlite.service.ExecutiveService;
import com.upskill.supportflowlite.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ExecutiveService executiveService;

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
                                          @RequestBody TicketDto ticketDto ,
                                          Ticket ticket){
        try {
            //Step 1: Fetch Customer Obj using username
            Customer customer = customerService.fetchCustomerByUsername(customerUsername);
            //Step 2: Attach customer to ticket
            ticket.setCustomer(customer);
            //Step 3: Pass all info from DTO to Ticket
            ticket.setStatus(Status.OPEN);
            ticket.setSubject(ticketDto.getSubject());
            ticket.setMessage(ticketDto.getMessage());
            ticket.setPriority(Priority.valueOf(ticketDto.getPriority()));
            // Step 4: Save the ticket
            return ResponseEntity.ok(ticketService.save(ticket));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /*
    * Take ticketId and executiveId as PathVariables
    * fetch objects and validate
    * update existing ticket with executive object
    * save ticket -- since id is already present-it will act as update
    * */
    @PostMapping("/executive/assign/{ticketId}/{executiveId}")
    public Ticket assignTicketToExecutive(@PathVariable int ticketId ,
                                        @PathVariable int executiveId){

        Ticket ticket = ticketService.getTicketById(ticketId);
        Executive executive =  executiveService.getExecutiveById(executiveId);

        ticket.setExecutive(executive);
        return ticketService.save(ticket);
    }

    /* API to close the ticket
    * Take ticketId as Path Variable */
    @PutMapping("/close/{ticketId}")
    public Ticket closeTicket(@PathVariable int ticketId){

        Ticket ticket = ticketService.getTicketById(ticketId);
        ticket.setStatus(Status.CLOSED);
        ticket.setClosedAt(LocalDateTime.now());
        return ticketService.save(ticket);
    }

    /*
    * AIM: fetch all tickets that were closed on the same day
    * */
    @GetMapping("/all/same-day")
    public List<Ticket> getTicketsClosedOnSameDay(@RequestParam Integer page,
                                                  @RequestParam Integer size){

        return ticketService.getTicketsClosedOnSameDay(page,size);
    }

}
