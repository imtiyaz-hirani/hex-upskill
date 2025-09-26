package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer fetchCustomerById(int customerId) {
       Optional<Customer> optional =  customerRepository.findById(customerId);
       if(optional.isEmpty())
           throw new RuntimeException("Invalid Id given");

       return optional.get();
    }

    public Customer fetchCustomerByUsername(String customerUsername) {
        Customer customer = customerRepository.findByUserUsername(customerUsername);
        if(customer == null)
            throw new RuntimeException("Invalid Customer Username");

        return customer;
    }
}
