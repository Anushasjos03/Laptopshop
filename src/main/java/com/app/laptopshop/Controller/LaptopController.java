package com.app.laptopshop.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.laptopshop.Entity.Laptop;
import com.app.laptopshop.Service.LaptopService;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    // Create a new laptop with supplierId
    @PostMapping("/{supplierId}")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @PathVariable Long supplierId) {
        // Pass the supplierId along with the laptop to the service method
        Laptop savedLaptop = laptopService.createLaptop(laptop, supplierId);
        return new ResponseEntity<>(savedLaptop, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")  // Renamed to 'id'
    public ResponseEntity<Laptop> get(@PathVariable Long id) {
        return ResponseEntity.ok(laptopService.getLaptop(id));
    }

    @GetMapping
    public List<Laptop> getAll() {
        return laptopService.getAllLaptops();
    }

    @PutMapping("/{id}")  // Renamed to 'id'
    public ResponseEntity<Laptop> update(@PathVariable Long id, @RequestBody Laptop laptop) {
        return ResponseEntity.ok(laptopService.updateLaptop(id, laptop));
    }

    @DeleteMapping("/{id}")  // Renamed to 'id'
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laptopService.deleteLaptop(id);
        return ResponseEntity.noContent().build();
    }
}
