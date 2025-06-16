package com.app.laptopshop.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // Renamed to 'id'
    private String name;
    private String email;
    private String contactNumber;

    @Version  // Add this annotation for versioning
    private Integer version;

   @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
   @JsonIgnore // <-- Add this annotation
   private List<Laptop> laptops;

    
}
