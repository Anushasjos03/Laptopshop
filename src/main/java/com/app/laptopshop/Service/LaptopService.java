package com.app.laptopshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.laptopshop.Entity.Laptop;
import com.app.laptopshop.Repository.LaptopRepo;
import com.app.laptopshop.Entity.Supplier; 
import com.app.laptopshop.Repository.SupplierRepo; 

import java.util.List;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepo laptopRepo;

    @Autowired
    private SupplierRepo supplierRepo; 

    // Create a new Laptop
    @Transactional
    public Laptop createLaptop(Laptop laptop, Long supplierId) {
        Supplier supplier = supplierRepo.findById(supplierId).orElseThrow(() -> new RuntimeException("Supplier not found"));

        laptop.setSupplier(supplier);
        return laptopRepo.save(laptop);
    }

    // Get a laptop by ID
    public Laptop getLaptop(Long id) {
        return laptopRepo.findById(id).orElseThrow(() -> new RuntimeException("Laptop not found with id: " + id));
    }

    // Get all laptops
    public List<Laptop> getAllLaptops() {
        return laptopRepo.findAll();
    }

    // Update laptop by ID
    public Laptop updateLaptop(Long id, Laptop updatedLaptop) {
        Laptop existingLaptop = getLaptop(id); // This will throw if laptop does not exist
        existingLaptop.setBrand(updatedLaptop.getBrand());
        existingLaptop.setModel(updatedLaptop.getModel());
        existingLaptop.setPrice(updatedLaptop.getPrice());
        return laptopRepo.save(existingLaptop);
    }

    // Delete laptop by ID
    public void deleteLaptop(Long id) {
        laptopRepo.deleteById(id);
    }
}
