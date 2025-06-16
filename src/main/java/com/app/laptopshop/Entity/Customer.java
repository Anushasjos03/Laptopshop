package com.app.laptopshop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Change to IDENTITY if you want auto-increment in MySQL
    private Long id; 

    private String name;
    private String email;
    private String phone;
    private String address;

    

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders;

    
}
