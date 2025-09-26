package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.Ticket;
import com.upskill.supportflowlite.repository.TicketRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Ticket> getTicketsClosedOnSameDay(Integer page, Integer size) {
        //Pageable pageable = PageRequest.of(page,size);
        /* Fetch all tickets */
        List<Ticket> list = ticketRepository.findAll();
        /* Give it to streams - filter and return the list */
        return list.parallelStream()
                .filter(t->t.getClosedAt() != null)
                .filter(t->t.getCreatedAt() != null)
                .filter(t-> t.getClosedAt().isEqual(t.getCreatedAt()))
                .toList().stream().limit(size).skip((long)page*size).toList();
    }
}
