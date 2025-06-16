package com.app.laptopshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.laptopshop.Entity.Laptop;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {

    // Search for laptops by brand (case insensitive search)
    @Query("SELECT l FROM Laptop l WHERE LOWER(l.brand) LIKE LOWER(CONCAT('%', :brand, '%'))")
    List<Laptop> findByBrand(@Param("brand") String brand);

    // Search for laptops by price range
    @Query("SELECT l FROM Laptop l WHERE l.price BETWEEN :minPrice AND :maxPrice")
    List<Laptop> findLaptopsByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

}
