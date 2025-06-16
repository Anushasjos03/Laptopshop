package com.app.laptopshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.laptopshop.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    // Search for customers by name (case insensitive search)
    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Customer> findByName(@Param("name") String name);

    // Search for customers by email (exact match)
    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    List<Customer> findByEmail(@Param("email") String email);

    // Find all customers sorted by name
    List<Customer> findAllByOrderByNameAsc();  // Ascending order by name
    List<Customer> findAllByOrderByNameDesc(); // Descending order by name
}
