package com.upskill.supportflowlite.repository;

import com.upskill.supportflowlite.model.CustomerProduct;
import com.upskill.supportflowlite.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Integer> {

    @Query("select cp.product from CustomerProduct cp where cp.customer.id=?1")
    List<Product> getProductsByCustomerId(int customerId);
}
