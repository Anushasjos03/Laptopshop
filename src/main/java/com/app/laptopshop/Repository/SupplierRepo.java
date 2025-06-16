package com.app.laptopshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.laptopshop.Entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

    // Search for suppliers by name (case insensitive search)
    @Query("SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Supplier> findByName(@Param("name") String name);

    // Search for suppliers by email (exact match)
    @Query("SELECT s FROM Supplier s WHERE s.email = :email")
    List<Supplier> findByEmail(@Param("email") String email);

}
