package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.CustomerProduct;
import com.upskill.supportflowlite.repository.CustomerProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerProductService {
    private final CustomerProductRepository customerProductRepository;

    public CustomerProductService(CustomerProductRepository customerProductRepository) {
        this.customerProductRepository = customerProductRepository;
    }

    public CustomerProduct save(CustomerProduct customerProduct) {
        return customerProductRepository.save(customerProduct);
    }
}
