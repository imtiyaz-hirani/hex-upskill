package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.Product;
import com.upskill.supportflowlite.repository.CustomerProductRepository;
import com.upskill.supportflowlite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomerProductRepository customerProductRepository;

    public ProductService(ProductRepository productRepository, CustomerProductRepository customerProductRepository) {
        this.productRepository = productRepository;
        this.customerProductRepository = customerProductRepository;
    }

    public Product fetchById(int pid) {
       Optional<Product> optional = productRepository.findById(pid);
       if(optional.isEmpty())
           throw new RuntimeException("Invalid Product Id Given");

       return optional.get();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByCustomerId(int customerId) {
        return customerProductRepository.getProductsByCustomerId(customerId);
    }
}
