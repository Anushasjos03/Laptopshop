package com.app.laptopshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.laptopshop.Entity.Laptop;
import com.app.laptopshop.Entity.Supplier;
import com.app.laptopshop.Repository.SupplierRepo;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    // Create a new Supplier
    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        if (supplier.getLaptops() != null) {
            for (Laptop laptop : supplier.getLaptops()) {
                laptop.setSupplier(supplier);
            }
        }
        return supplierRepo.save(supplier);
    }

    // Get a supplier by ID
    public Supplier getSupplier(Long id) {
        return supplierRepo.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    // Update supplier by ID
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier existingSupplier = getSupplier(id); // This will throw if supplier does not exist
        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setEmail(updatedSupplier.getEmail());
        existingSupplier.setContactNumber(updatedSupplier.getContactNumber());
        return supplierRepo.save(existingSupplier);
    }

    // Delete supplier by ID
    public void deleteSupplier(Long id) {
        supplierRepo.deleteById(id);
    }
}
