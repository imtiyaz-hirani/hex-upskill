package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.dto.CustomerProductDto;
import com.upskill.supportflowlite.model.Customer;
import com.upskill.supportflowlite.model.CustomerProduct;
import com.upskill.supportflowlite.model.Product;
import com.upskill.supportflowlite.service.CustomerProductService;
import com.upskill.supportflowlite.service.CustomerService;
import com.upskill.supportflowlite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerProductController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerProductService customerProductService;

    @PostMapping("/api/customer/product/add/{pid}/{cid}")
    public ResponseEntity<?> addCustomerProduct(@PathVariable int pid,
                                   @PathVariable int cid,
                                   @RequestBody(required = false) CustomerProduct customerProduct){
        try {
            // Step 1: Fetch product Obj using pid
            Product product = productService.fetchById(pid);
            // Step 2: Fetch Customer obj using cid
            Customer customer = customerService.fetchCustomerById(cid);
            // Step 3: Attach product and customer to customerProduct obj
            customerProduct.setCustomer(customer);
            customerProduct.setProduct(product);
            // Step 4: Save customerProduct obj in DB
            return ResponseEntity.ok(customerProductService.save(customerProduct));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * {
     *     "customerId" :"",
     *     "productId": "",
     *     "discount": "10"
     * }
     */
    @PostMapping("/api/customer/product/add/v2")
    public ResponseEntity<?> addCustomerProductV2(@RequestBody CustomerProductDto customerProductDto,
                                     CustomerProduct customerProduct){
        try {
            // Step 1: Fetch product Obj using pid
            Product product = productService.fetchById(customerProductDto.getProductId());
            // Step 2: Fetch Customer obj using cid
            Customer customer = customerService.fetchCustomerById(customerProductDto.getCustomerId());
            // Step 3: Attach product and customer to customerProduct obj
            customerProduct.setCustomer(customer);
            customerProduct.setProduct(product);

            customerProduct.setDiscount(customerProductDto.getDiscount());
            // Step 4: Save customerProduct obj in DB
            return ResponseEntity.ok(customerProductService.save(customerProduct));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
