package com.upskill.supportflowlite.repository;

import com.upskill.supportflowlite.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("select t from Ticket t where t.customer.id=?1")
    List<Ticket> getTicketsByCustomerId(int customerId);
}
