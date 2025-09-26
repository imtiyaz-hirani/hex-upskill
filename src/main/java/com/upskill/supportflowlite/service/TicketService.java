package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.Ticket;
import com.upskill.supportflowlite.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(int ticketId) {
       Optional<Ticket> optional =  ticketRepository.findById(ticketId);
       if(optional.isEmpty())
           throw new RuntimeException("Invalid Ticket Id Given");
       return optional.get();
    }

    public List<Ticket> getTicketsByCustomerId(int customerId) {
        return ticketRepository.getTicketsByCustomerId(customerId);
    }
}
