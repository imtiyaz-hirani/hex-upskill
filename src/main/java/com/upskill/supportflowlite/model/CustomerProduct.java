package com.upskill.supportflowlite.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer_product")
public class CustomerProduct { //cp

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //findById

    @ManyToOne
    private Customer customer; //1:1  M:1
    @ManyToOne
    private Product product; //1:1 M:1

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase = LocalDate.now();

    private double discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
