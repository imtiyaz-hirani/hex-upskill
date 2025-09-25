package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.Ticket;
import com.upskill.supportflowlite.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
