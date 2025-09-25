package com.upskill.supportflowlite.repository;

import com.upskill.supportflowlite.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
