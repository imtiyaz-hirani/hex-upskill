package com.upskill.supportflowlite.repository;


import com.upskill.supportflowlite.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUserUsername(String customerUsername);
}
