package com.app.laptopshop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")  // Avoid SQL keyword conflict
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // Primary key

    private Date orderDate;  // Order date
    private Double totalAmount;  // Total amount
    private String status;  // Order status

    @ManyToOne
    @JoinColumn(name = "customerId")  // Foreign key for Customer
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "laptopId")  // Foreign key for Laptop
    private Laptop laptop;

   
}
