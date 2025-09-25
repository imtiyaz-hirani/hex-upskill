package com.upskill.supportflowlite.repository;

import com.upskill.supportflowlite.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
