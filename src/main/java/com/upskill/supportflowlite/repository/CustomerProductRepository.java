package com.upskill.supportflowlite.repository;

import com.upskill.supportflowlite.model.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Integer> {

}
