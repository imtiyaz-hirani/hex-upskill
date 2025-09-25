package com.upskill.supportflowlite.controller;

import com.upskill.supportflowlite.model.Product;
import com.upskill.supportflowlite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    public Product postProduct(@RequestBody Product product){
          return productService.save(product);
    }
}
